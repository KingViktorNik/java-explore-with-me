package ru.practicum.ewm.privats.service.rating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.part_request.enums.RequestStatus;
import ru.practicum.ewm.model.rating.Rating;
import ru.practicum.ewm.repository.EventRepository;
import ru.practicum.ewm.repository.PartRequestRepository;
import ru.practicum.ewm.repository.RatingRepository;
import ru.practicum.ewm.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static ru.practicum.ewm.model.part_request.enums.RequestStatus.*;

@Slf4j
@Service
@AllArgsConstructor
public class RatingPrivateServiceImpl implements RatingPrivateService {
    private final LocalDateTime dateTime = LocalDateTime.now();
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PartRequestRepository requestRepository;

    @Override
    public void updateLike(Long userId, Long eventId, Boolean like) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));

        Event event = eventRepository.findById(eventId)
                                  .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));

        // Оценить можно только завершенное событие, которое уже прошло
        if (event.getEventDate().isAfter(dateTime)) {
            throw new ConflictException("Event id=" + eventId + " has not yet taken place");
        }

        // Организатор события не может оценить событие.
        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Event organizer id =" + userId + " cannot evaluate the event.");
        }

        // Если событию не требуется модерация, выбирается соответствующий статус участника.
        RequestStatus status;
        if (event.getRequestModeration()) {
            status = CONFIRMED;
        } else {
            status = PENDING;
        }

        // Является ли пользователь участником
        requestRepository.findByRequesterIdAndEventIdAndStatus(userId, eventId, status)
                         .orElseThrow(() -> new NotFoundException("Participation Request with id=" + userId + " was not found"));


        // Проверка наличия оценки в базе, оценивал ли пользователь событие ранее.
        Optional<Rating> result = ratingRepository.findByUserIdAndEventId(userId, eventId);

        Long ratingId = null;
        if (result.isPresent()) {
            ratingId = result.get().getId();
        }

        Rating ratingEvent = new Rating(ratingId, user, event, like);
        log.info("addLike eventId:{} userId{}", eventId, userId);

        ratingRepository.save(ratingEvent);

    }

    @Override
    public void deleteLike(Long userId, Long eventId) {
        Rating event = ratingRepository.findByUserIdAndEventId(userId, eventId)
                                            .orElseThrow(() -> new NotFoundException("Event rating not found"));
        ratingRepository.deleteById(event.getId());
        log.info(" deleteLike likeId= {}, eventId={}, userId={}", event.getId(), eventId, userId);

    }

}
