package ru.practicum.ewm.model.event.enums;

import java.util.Optional;

public enum EventState {
    /** @PENDING - В ОЖИДАНИИ **/
    PENDING,

    /** @PUBLISHED - ОПУБЛИКОВАНО **/
    PUBLISHED,

    /** @CANCELED - ОТМЕНЕНО **/
    CANCELED;

    public static Optional<EventState> from(String stringState) {
        for (EventState state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
