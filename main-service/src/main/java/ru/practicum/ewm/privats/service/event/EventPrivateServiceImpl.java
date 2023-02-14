package ru.practicum.ewm.privats.service.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateRequestDto;
import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateResultDto;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.mapper.EventMapper;
import ru.practicum.ewm.mapper.PartRequestMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.partRequest.PartRequest;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.partRequest.enums.RequestStatus;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.dto.event.EventNewDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.model.event.enums.EventStateAction;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NullObjectException;
import ru.practicum.ewm.exception.ValidationException;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.repository.PartRequestRepository;
import ru.practicum.ewm.repository.UserRepository;
import ru.practicum.ewm.util.DateTimeConverter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class EventPrivateServiceImpl implements EventPrivateService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final PartRequestRepository partRequestRepository;

    @Override
    public EventFullDto addEvent(@Positive(message = "id is not correct") Long userId, EventNewDto eventNewDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));

        Category category = categoryRepository.findById(eventNewDto.getCategory())
                .orElseThrow(() -> new NullObjectException("Category with id=" + eventNewDto.getCategory() + " was not found"));

        Event event = EventMapper.toEntityNew(eventNewDto);
        event.setInitiator(user);
        event.setCategory(category);
        event.setState(EventState.PENDING);
        event.setConfirmedRequests(0);
        event.setAvailable(true);
        event.setViews(0);
        event.setCreatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

//         дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента
        if (!event.getCreatedOn().plusHours(2).minusSeconds(1).isBefore(event.getEventDate())) {
            throw new ConflictException("For the requested operation the conditions are not met.");
        }

        EventFullDto eventFullDto = EventMapper.toFullDto(eventRepository.save(event));

        log.info("[POST] addEvent id:{}/initiatorId:{}/CategoryId:{}/dateCreated:{}",
                event.getId(),
                event.getInitiator().getId(),
                event.getCategory().getId(),
                DateTimeConverter.toString(event.getCreatedOn())
        );

        return eventFullDto;
    }

    @Override
    public EventFullDto updateEvent(Long userId, Long eventId, EventUpdateDto eventUpdateDto) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));

        Event event = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NullObjectException("Event with id=" + eventId + " was not found"));

        // изменить можно только отмененные события или события в состоянии ожидания модерации
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("could not execute statement; SQL [n/a]; constraint [uq_category_name]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement");
        }

        event = EventMapper.toEntityUpdate(eventUpdateDto, event);

        // дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента
        if (!LocalDateTime.now().plusHours(2).minusSeconds(1).isBefore(event.getEventDate())) {
            throw new ConflictException("Only pending or canceled events can be changed");
        }

        // изменить можно только отмененные события или события в состоянии ожидания модерации
        EventStateAction stateAction = EventStateAction.from(eventUpdateDto.getStateAction())
                .orElseThrow(() -> new ValidationException("Event must not be published"));

        if (stateAction == EventStateAction.SEND_TO_REVIEW) {
            event.setState(EventState.PENDING);
        } else if (stateAction == EventStateAction.CANCEL_REVIEW) {
            event.setState(EventState.CANCELED);
        } else
            throw new NullObjectException("Event must not be published");

        EventFullDto eventFullDto = EventMapper.toFullDto(eventRepository.save(event));
        log.info("[PATCH] updateEvent id:{}/initiatorId:{}/CategoryId:{}/dateCreated:{}",
                eventFullDto.getId(),
                eventFullDto.getInitiator().getId(),
                eventFullDto.getCategory().getId(),
                DateTimeConverter.toString(event.getCreatedOn())
        );

        return eventFullDto;
    }

    @Override
    public List<EventShortDto> getEvents(Long userId, Integer from, Integer size) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));

        return eventRepository.findAllByInitiator_Id(userId, PageRequest.of(from, size)).stream()
                .map(EventMapper::toShortDto)
                .sorted(Comparator.comparing(EventShortDto::getEventDate))
                .collect(toList());
    }

    @Override
    public EventFullDto getEvent(Long userId, Long eventId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("Incorrectly made request."));

        Event event = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NullObjectException("Event with id=" + eventId + " was not found"));

        return EventMapper.toFullDto(event);
    }

    @Override
    public EventRequestStatusUpdateResultDto updateRequests(Long userId, Long eventId, EventRequestStatusUpdateRequestDto dto) {
        RequestStatus status = RequestStatus.from(dto.getStatus())
                .orElseThrow(()-> new ValidationException("The request was made incorrectly"));
        Event event = eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NullObjectException("Event with id=" + eventId + " was not found"));


        //нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
        if (!event.getAvailable()) {
            throw new ConflictException("The participant limit has been reached");
        }


        int limit = event.getConfirmedRequests();
        List<PartRequest> requests = partRequestRepository.getByEventIdAndIdIn(eventId, dto.getRequestIds());
        List<PartRequest> confirmedRequests = new ArrayList<>();
        List<PartRequest> rejectedRequests = new ArrayList<>();

        for (PartRequest request : requests) {
            //статус можно изменить только у заявок, находящихся в состоянии ожидания
            if (request.getStatus() != RequestStatus.PENDING && event.getState() != EventState.PUBLISHED) {
                throw new ValidationException("Request must have status PENDING");
            }
            if (limit < event.getParticipantLimit() || event.getParticipantLimit() == 0) {
                if (status.equals(RequestStatus.CONFIRMED)) {
                    limit++;
                    request.setStatus(status);
                    confirmedRequests.add(request);
                } else {
                    if (event.getParticipantLimit() != 0) {
                        limit--;
                    }
                    request.setStatus(status);
                    rejectedRequests.add(request);
                }
            }   else {
                request.setStatus(RequestStatus.REJECTED);
                rejectedRequests.add(request);
            }
        }
        if (event.getConfirmedRequests().equals(event.getParticipantLimit())) {
            event.setAvailable(true);
        } else {
            event.setAvailable(false);
        }
        List<PartRequest> result = new ArrayList<>(rejectedRequests);
        result.addAll(confirmedRequests);
        if (!result.isEmpty()) {
            partRequestRepository.saveAll(result);
        }
        eventRepository.save(event);
        return new EventRequestStatusUpdateResultDto(PartRequestMapper.toDtos(confirmedRequests),
                                                     PartRequestMapper.toDtos(rejectedRequests));
    }

    @Override
    public List<PartRequestDto> getRequests(Long userId, Long eventId) {
        eventRepository.findByIdAndInitiator_Id(eventId, userId)
                .orElseThrow(() -> new NullObjectException("Event with id=" + eventId + " was not found"));

        List<PartRequest> requests = partRequestRepository.getByEventId(eventId);
        return PartRequestMapper.toDtos(requests);
    }
}
