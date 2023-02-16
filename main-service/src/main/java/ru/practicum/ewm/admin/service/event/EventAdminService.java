package ru.practicum.ewm.admin.service.event;

import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventInquiryDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;

import java.util.List;

public interface EventAdminService {
    /**
     * Поиск событий
     *
     * @param eventInquiryDto новое событие
     * @return List < EventFullDto >
     **/
    List<EventFullDto> getEvents(EventInquiryDto eventInquiryDto);

    /**
     * Редактирование данных события и его статуса
     *
     * @param eventId   идентификатор события
     * @param updateDto новое событие
     * @return EventFullDto
     **/
    EventFullDto updateEvent(Long eventId, EventUpdateDto updateDto);

}
