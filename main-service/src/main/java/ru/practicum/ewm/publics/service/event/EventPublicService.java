package ru.practicum.ewm.publics.service.event;

import ru.practicum.ewm.dto.event.EventFullDto;
import ru.practicum.ewm.dto.event.EventShortDto;
import ru.practicum.ewm.dto.event.EventShortInquiryDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventPublicService {
    /** Получение событий с возможностью фильтрации **/
    List<EventShortDto> getEvents(EventShortInquiryDto dto, HttpServletRequest request);

    /** Получение подробной информации об опубликованном событии по его идентификатору **/
    EventFullDto getEvent(Long id, HttpServletRequest request);

}
