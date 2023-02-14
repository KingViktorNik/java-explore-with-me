package ru.practicum.ewm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.model.event.Event;
import ru.practicum.ewm.model.event.EventShort;
import ru.practicum.ewm.model.event.enums.EventState;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, QuerydslPredicateExecutor<Event> {

    /** Получение события пользователя по идентификатору **/
    Optional<Event> findByIdAndInitiator_Id(Long eventId, Long userId);

    /** Запрос по списку идентификаторов событий **/
    Optional<Set<Event>> findByIdIn(Set<Long> eventsId);

    /** Запрос по идентификатору и статусу **/
    Optional<Event> findByIdAndState(Long eventsId, EventState eventState);

    /** Запрос по идентификатору организатору события **/
    Page<EventShort> findAllByInitiator_Id(Long userId, Pageable pageable);

}