package ru.practicum.ewm.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventShortInquiryDto {
    /** текст для поиска в содержимом аннотации и подробном описании события */
    private final String text;

    /** список идентификаторов категорий в которых будет вестись поиск */
    private final Set<Long> categories;

    /**
     * поиск:
     *
     * TRUE - платных;
     * FALSE - бесплатных событий;
     * NULL - все;
     **/
    private final Boolean paid;

    /** Дата и время начало 'С' */
    private final LocalDateTime start;

    /** Дата и время окончания 'ДО' */
    private final LocalDateTime end;


    /**
     * только события у которых:
     *
     * TRUE - не исчерпан лимит запросов на участие;
     * FALSE - все;
     **/
    private final Boolean onlyAvailable;

    /**
     * Вариант сортировки:
     *
     * EVENT_DATE - по дате события
     * VIEWS - по количеству просмотров
     **/
    private final EventSort sort;

    /** PageRequest страница **/
    private final Integer from;

    /** PageRequest количество элементов **/
    private final Integer size;


    public enum EventSort {
        /** по дате */
        EVENT_DATE,

        /** по количеству просмотров */
        VIEWS;

        public static Optional<EventSort> from(String stringState) {
            for (EventSort state : values()) {
                if (state.name().equalsIgnoreCase(stringState)) {
                    return Optional.of(state);
                }
            }
            return Optional.empty();
        }
    }
}
