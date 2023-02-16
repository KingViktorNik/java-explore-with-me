package ru.practicum.ewm.model.part_request.enums;

import java.util.Optional;

public enum RequestStatus {
    /** ПОДТВЕРЖДЕН */
    CONFIRMED,

    /** В ОЖИДАНИИ */
    PENDING,

    /** ОТКЛОНЕН */
    REJECTED,

    /** ОТМЕНЕН */
    CANCELED;

    public static Optional<RequestStatus> from(String stringState) {
        for (RequestStatus state : values()) {
            if (state.name().equalsIgnoreCase(stringState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
