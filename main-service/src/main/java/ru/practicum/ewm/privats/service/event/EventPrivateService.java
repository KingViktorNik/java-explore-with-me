package ru.practicum.ewm.privats.service.event;

import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateRequestDto;
import ru.practicum.ewm.dto.partRequest.EventRequestStatusUpdateResultDto;
import ru.practicum.ewm.dto.partRequest.PartRequestDto;
import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.EventNewDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;

import java.util.List;

public interface EventPrivateService {
    /**
     * Добавление нового события
     *
     * @param userId      идентификатор пользователя(инициатора)
     * @param eventNewDto новое событие
     * @return EventFullDto
     **/
    EventFullDto addEvent(Long userId, EventNewDto eventNewDto);

    /**
     * Получение событий, добавленных текущим пользователем(инициатором)
     *
     * @param userId идентификатор пользователя(инициатора)
     * @param from   PageRequest страница
     * @param size   PageRequest количество элементов
     * @return List < EventShortDto >
     **/
    List<EventShortDto> getEvents(Long userId, Integer from, Integer size);

    /**
     * Получение событие пользователя(инициатора)
     *
     * @param userId  идентификатор пользователя(инициатора)
     * @param eventId идентификатор события
     * @return EventFullDto
     **/
    EventFullDto getEvent(Long userId, Long eventId);

    /**
     * Изменить данные о событии
     *
     * @param userId         идентификатор пользователя(инициатора)
     * @param eventId        идентификатор события
     * @param eventUpdateDto новое событие
     * @return EventFullDto
     **/
    EventFullDto updateEvent(Long userId, Long eventId, EventUpdateDto eventUpdateDto);

    /**
     * Изменение статуса заявок на участие в событии текущего пользователя
     *
     * @param userId           идентификатор пользователя(инициатора)
     * @param eventId          идентификатор события
     * @param updateRequestDto новый статус заявки на участие
     * @return EventRequestStatusUpdateResultDto
     **/
    EventRequestStatusUpdateResultDto updateRequests(Long userId, Long eventId, EventRequestStatusUpdateRequestDto updateRequestDto);

    /**
     * Получение информации о запросах на участие в событии текущего пользователя
     *
     * @param userId  идентификатор пользователя
     * @param eventId идентификатор события
     * @return List < PartRequestDto >
     **/
    List<PartRequestDto> getRequests(Long userId, Long eventId);

}
