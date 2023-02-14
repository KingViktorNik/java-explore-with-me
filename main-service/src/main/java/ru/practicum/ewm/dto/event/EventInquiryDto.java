package ru.practicum.ewm.dto.event;

import lombok.*;
import ru.practicum.ewm.model.event.enums.EventState;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class EventInquiryDto {
    /**
     * список id пользователей
     **/
    private final Set<Long> users;

    /**
     * список состояний
     **/
    private Set<EventState> states;

    /**
     * список id категорий
     **/
    private final Set<Long> categories;

    /**
     * Период времени событий
     **/
    private final LocalDateTime start;

    private final LocalDateTime end;

    /**
     * Пагинация
     **/
    private final Integer from;

    private final Integer size;

}
