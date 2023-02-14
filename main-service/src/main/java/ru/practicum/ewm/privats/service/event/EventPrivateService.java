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
    /** Добавление нового события **/
    EventFullDto addEvent(Long userId, EventNewDto eventNewDto);

    /** Получение событий, добавленных текущим пользователем **/
    List<EventShortDto> getEvents(Long userId, Integer from, Integer size);

    /** Получение событие пользователя **/
    EventFullDto getEvent(Long userId, Long eventId);

    /** Изменить данные о событии **/
    EventFullDto updateEvent(Long userId, Long eventId, EventUpdateDto eventUpdateDto);

    /** Изменение статуса (подтверждена, отменена) заявок на участие в событии текущего пользователя **/
    EventRequestStatusUpdateResultDto updateRequests(Long userId, Long eventId, EventRequestStatusUpdateRequestDto dto);

    /** Получение информации о запросах на участие в событии текущего пользователя **/
    List<PartRequestDto> getRequests(Long userId, Long eventId);
}
