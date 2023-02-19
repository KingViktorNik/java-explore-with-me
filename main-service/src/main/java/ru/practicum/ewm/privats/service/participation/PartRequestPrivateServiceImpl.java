package ru.practicum.ewm.privats.service.participation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.mapper.PartRequestMapper;
import ru.practicum.ewm.model.part_request.PartRequest;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.repository.PartRequestRepository;
import ru.practicum.ewm.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.ewm.model.part_request.enums.RequestStatus.CANCELED;
import static ru.practicum.ewm.model.part_request.enums.RequestStatus.PENDING;

@Slf4j
@Service
@AllArgsConstructor
public class PartRequestPrivateServiceImpl implements PartRequestPrivateService {
    private final PartRequestRepository partRequestRepository;
    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public PartRequestDto addParticipationRequest(Long userId, Long eventId) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));

        Event event = eventRepository.findById(eventId)
                                     .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));

        // инициатор события не может добавить запрос на участие в своём событии
        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

        // нельзя участвовать в неопубликованном событии
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

        // если у события достигнут лимит запросов на участие - необходимо вернуть ошибку
        if (!event.getAvailable() && event.getParticipantLimit() > 0) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

        // если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного
        int confirmedRequests = event.getConfirmedRequests();
        if (!event.getRequestModeration()) {
            confirmedRequests = event.getConfirmedRequests() + 1;
        }

        event.setConfirmedRequests(confirmedRequests);

        PartRequest result = PartRequest.builder()
                                        .created(LocalDateTime.now())
                                        .eventId(eventId)
                                        .requesterId(userId)
                                        .status(PENDING)
                                        .build();

        // проверка лимита заявок
        event.setAvailable(event.getParticipantLimit() == 0 ||
                          !event.getParticipantLimit().equals(event.getConfirmedRequests()));

        eventRepository.save(event);
        PartRequest partRequest = partRequestRepository.save(result);

        return PartRequestMapper.toDto(partRequest);

    }

    @Override
    public PartRequestDto canselParticipationRequest(Long userId, Long requestId) {
        PartRequest request = partRequestRepository.findByRequesterIdAndId(userId, requestId)
                                                   .orElseThrow(() -> new NotFoundException("Request with id=" + requestId + " was not found"));


        Event event = eventRepository.findById(request.getEventId())
                                     .orElseThrow(() -> new NotFoundException("Event with id=" + request.getEventId() + " was not found"));

        request.setStatus(CANCELED);

        // изменение кол-во участников
        event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        event.setAvailable(true);

        eventRepository.save(event);
        partRequestRepository.save(request);

        return PartRequestMapper.toDto(request);

    }

    @Override
    public List<PartRequestDto> getParticipationRequests(Long userId) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));

        List<PartRequest> requests = partRequestRepository.getByRequesterId(userId);

        return PartRequestMapper.toDtos(requests);

    }

}
