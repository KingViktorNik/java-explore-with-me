package ru.practicum.ewm.admin.service.event;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventInquiryDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.exception.*;
import ru.practicum.ewm.mapper.EventMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.enums.EventStateAction;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.util.QPredicates;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.ewm.model.event.QEvent.event;
import static ru.practicum.ewm.model.event.enums.EventState.*;
import static ru.practicum.ewm.model.event.enums.EventStateAction.*;


@Slf4j
@Service
@AllArgsConstructor
public class EventAdminServiceImpl implements EventAdminService {
    private final LocalDateTime dateTime = LocalDateTime.now();
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<EventFullDto> getEvents(EventInquiryDto dto) {
        Predicate predicate = QPredicates.builder()
                                         .add(dto.getUsers(), event.initiator.id::in)
                                         .add(dto.getStates(), event.state::in)
                                         .add(dto.getCategories(), event.category.id::in)
                                         .add(dto.getStart(), event.eventDate::after)
                                         .add(dto.getEnd(), event.eventDate::before)
                                         .buildAnd();

        return eventRepository.findAll(predicate,
                                       PageRequest.of(dto.getFrom(), dto.getSize()))
                              .stream()
                              .map(EventMapper::toFullDto)
                              .collect(toList());

    }

    @Override
    public EventFullDto updateEvent(Long eventId, EventUpdateDto updateDto) {
        Event event = eventRepository.findById(eventId)
                                     .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));
        EventMapper.toEntityUpdate(updateDto, event);

        //дата начала изменяемого события должна быть не ранее чем за час от даты публикации.
        if (event.getEventDate().isBefore(dateTime.minusHours(1))) {
            throw new ConflictException("the start date of the event to be changed must be no earlier than one hour from the publication date");
        }

        // событие можно публиковать, только если оно в состоянии ожидания публикации
        if (!event.getState().equals(PENDING)) {
            throw new ConflictException("an event can only be published if it is in the 'publish' pending state");
        }

        EventStateAction stateAction;

        // событие можно отклонить, только если оно еще не опубликовано
        if (updateDto.getStateAction().equals("PUBLISH_EVENT")) {
            stateAction = PUBLISH_EVENT;
        } else if (updateDto.getStateAction().equals("REJECT_EVENT")) {
            stateAction = CANCEL_REVIEW;
        } else {
            throw new ValidationException("Incorrectly made request.");
        }

        // обновления данных о категории
        if (updateDto.getCategory() != null) {
            Category category = categoryRepository.findById(updateDto.getCategory())
                                                  .orElseThrow(() -> new ConflictException("Event with id=" + eventId + " was not found"));
            event.setCategory(category);
        }

        // статус события
        if (stateAction.equals(PUBLISH_EVENT)) {
            event.setState(PUBLISHED);
            event.setPublishedOn(dateTime);
        } else {
            event.setState(CANCELED);
        }

        eventRepository.save(event);

        return EventMapper.toFullDto(event);

    }

}


