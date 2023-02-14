package ru.practicum.ewm.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Getter
@AllArgsConstructor
public class EventShortInquiryDto {
    /** текст для поиска в содержимом аннотации и подробном описании события **/
    private final String text;

    /** список идентификаторов категорий в которых будет вестись поиск **/
    private final Set<Long> categories;

    /** поиск:
     * @TRUE    - платных
     * @FALSE   - бесплатных событий
     * @NULL    - все
     * **/
    private final Boolean paid;

    /** дата и время события  **/
    private final Range range;

    /** только события у которых:
     * @TRUE    - не исчерпан лимит запросов на участие
     * @FALSE   - все
     * **/
    private final Boolean onlyAvailable;

    /** Вариант сортировки:
     * @EVENT_DATE  - по дате события
     * @VIEWS       - по количеству просмотров
     * **/
    private final EventSort sort;

    /** Пагинация **/
    private final Page page;

    @Getter
    @AllArgsConstructor
    public final static class Range {
        /** Дата и время начало 'С' **/
        private final LocalDateTime start;

        /** Дата и время окончания 'ДО' **/
        private final LocalDateTime end;
    }

    @Getter
    @AllArgsConstructor
    public final static class Page {
        private final Integer from;
        private final Integer size;
    }


    public enum EventSort {
        /** по дате **/
        EVENT_DATE,

        /** по количеству просмотров **/
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
