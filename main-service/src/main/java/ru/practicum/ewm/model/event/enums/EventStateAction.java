package ru.practicum.ewm.model.event.enums;

import java.util.Optional;

public enum EventStateAction {
    /** SEND_TO_REVIEW - ОТПРАВИТЬ НА ПРОВЕРКУ **/
    SEND_TO_REVIEW,

    /** CANCEL_REVIEW - ОТМЕНИТЬ ПРОВЕРКУ **/
    CANCEL_REVIEW,

    /** PUBLISH_EVENT - ПУБЛИКАЦИЯ СОБЫТИЯ **/
    PUBLISH_EVENT;

    public static Optional<EventStateAction> from(String stringState) {
        for (EventStateAction state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
