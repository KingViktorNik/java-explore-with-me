package ru.practicum.ewm.admin.service.event;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventInquiryDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.ValidationException;
import ru.practicum.ewm.mapper.EventMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.model.event.enums.EventStateAction;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.util.QPredicates;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.ewm.model.event.QEvent.event;


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
        return eventRepository.findAll(predicate, PageRequest.of(dto.getFrom(), dto.getSize())).stream()
                .map(EventMapper::toFullDto)
                .collect(toList());
    }

    @Override
    public void getEven() {
        System.out.println();
    }

    @Override
    public EventFullDto updateEvent(Long eventId, EventUpdateDto updateDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NullPointerException("Event with id=" + eventId + " was not found"));
        EventMapper.toEntityUpdate(updateDto, event);

//         дата начала изменяемого события должна быть не ранее чем за час от даты публикации.
        if (event.getEventDate().isBefore(dateTime.minusHours(1))) {
            throw new ConflictException("For the requested operation the conditions are not met.");
        }

        // событие можно публиковать, только если оно в состоянии ожидания публикации
        if (!event.getState().equals(EventState.PENDING)) {
            throw new ConflictException("For the requested operation the conditions are not met.");
        }

        EventStateAction stateAction;
        // событие можно отклонить, только если оно еще не опубликовано
        if (updateDto.getStateAction().equals("PUBLISH_EVENT")) {
            stateAction = EventStateAction.PUBLISH_EVENT;
        } else if (updateDto.getStateAction().equals("REJECT_EVENT")) {
            stateAction = EventStateAction.CANCEL_REVIEW;
        } else {
            throw new ValidationException("Incorrectly made request.");
        }

        // обновления данных о категории
        if (updateDto.getCategory() != null) {
            Category category = categoryRepository.findById(updateDto.getCategory())
                    .orElseThrow(() -> new NullPointerException("Event with id=" + eventId + " was not found"));
            event.setCategory(category);
        }

        // статус события
        if (stateAction.equals(EventStateAction.PUBLISH_EVENT)) {
            event.setState(EventState.PUBLISHED);
        } else {
            event.setState(EventState.CANCELED);
        }
        eventRepository.save(event);

        return EventMapper.toFullDto(event);
    }
}


