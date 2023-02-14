package ru.practicum.ewm.privats.service.participation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NullObjectException;
import ru.practicum.ewm.mapper.PartRequestMapper;
import ru.practicum.ewm.model.partRequest.PartRequest;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.enums.EventState;
import ru.practicum.ewm.model.partRequest.enums.RequestStatus;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.repository.PartRequestRepository;
import ru.practicum.ewm.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PartRequestPrivateServiceImpl implements PartRequestPrivateService {
    private final PartRequestRepository partRequestRepository;
    private final UserRepository userRepository;

    private final EventRepository eventRepository;
    @Override
    public PartRequestDto addParticipationRequest(Long userId, Long eventId) {
        Optional<PartRequest> request = partRequestRepository.findByEventId(eventId);

        // нельзя добавить повторный запрос
//        if (request.isPresent()) {
//            throw new ConflictException("Event with id=13 was not found");
//        }

        userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NullObjectException("Event with id=" + eventId + " was not found"));

//        инициатор события не может добавить запрос на участие в своём событии
        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

//        нельзя участвовать в неопубликованном событии
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

//        если у события достигнут лимит запросов на участие - необходимо вернуть ошибку
        if (!event.getAvailable() && event.getParticipantLimit() > 0) {
            throw new ConflictException("Integrity constraint has been violated.");
        }

//        если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного
        event.setConfirmedRequests(!event.getRequestModeration() ? event.getConfirmedRequests() + 1 : event.getConfirmedRequests());

        PartRequest result = PartRequest.builder()
                .created(LocalDateTime.now())
                .eventId(eventId)
                .requesterId(userId)
                .status(RequestStatus.PENDING)
                .build();

        // проверка лимита заявок
        if (event.getParticipantLimit() != 0 && event.getParticipantLimit().equals(event.getConfirmedRequests())){
            event.setAvailable(false);
        } else {
            event.setAvailable(true);
        }

        Optional.of(eventRepository.save(event)).orElseThrow(()->new ConflictException(""));
        return PartRequestMapper.toDto(partRequestRepository.save(result));
    }

    @Override
    public PartRequestDto canselParticipationRequest(Long userId, Long requestId) {
        Optional<PartRequest> request = partRequestRepository.findByRequesterIdAndId(userId, requestId);
        if (request.isEmpty() )
            throw new NullObjectException("Request with id=" + requestId + " was not found");

        Event event = eventRepository.findById(request.get().getEventId())
                .orElseThrow(() -> new NullObjectException("Event with id=" + request.get().getEventId() + " was not found"));

        request.get().setStatus(RequestStatus.CANCELED);

        // изменение кол-во участников
        event.setConfirmedRequests(event.getConfirmedRequests() - 1);
        event.setAvailable(true);

        eventRepository.save(event);
        partRequestRepository.save(request.get());

        return PartRequestMapper.toDto(request.get());
    }

    @Override
    public List<PartRequestDto> getParticipationRequests(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NullObjectException("User with id=" + userId + " was not found"));

        List<PartRequest> requests = partRequestRepository.getByRequesterId(userId);
        return PartRequestMapper.toDtos(requests);
    }
}
