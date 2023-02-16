package ru.practicum.ewm.dto.event;

import lombok.*;
import ru.practicum.ewm.model.event.enums.EventState;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class EventInquiryDto {
    /** список id пользователей **/
    private final Set<Long> users;

    /** список состояний **/
    private Set<EventState> states;

    /** список id категорий **/
    private final Set<Long> categories;

    /** Период времени событий (начало)**/
    private final LocalDateTime start;

    /** Период времени событий (конец)**/
    private final LocalDateTime end;

    /** PageRequest страница **/
    private final Integer from;

    /** PageRequest количество элементов **/
    private final Integer size;

}
