package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.part_request.PartRequest;
import ru.practicum.ewm.model.part_request.enums.RequestStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PartRequestRepository extends JpaRepository<PartRequest, Long> {
    /** Запрос по id пользователя и id запроса на участия **/
    Optional<PartRequest> findByRequesterIdAndId(Long userId, Long requestId);

    /** Запрос по id события и по спуску id запроса на участия **/
    List<PartRequest> getByEventIdAndIdIn(Long eventId, Set<Long> requestId);

    /** Запрос по id события **/
    List<PartRequest> getByEventId(Long eventId);

    /** Запрос по id пользователя **/
    List<PartRequest> getByRequesterId(Long userId);

    /** Запрос по id пользователя и статусу **/
    Optional<PartRequest> findByRequesterIdAndStatus(Long userId, RequestStatus status);
}
