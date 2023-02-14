package ru.practicum.ewm.admin.service.event;

import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventInquiryDto;
import ru.practicum.ewm.dto.event.EventUpdateDto;

import java.util.List;

public interface EventAdminService {
    /**
     * Поиск событий
     **/
    List<EventFullDto> getEvents(EventInquiryDto dto);

    void getEven();

    /**
     * Редактирование данных события и его статуса (отклонение/публикация).
     **/
    EventFullDto updateEvent(Long eventId, EventUpdateDto updateDto);

}
