package ru.practicum.ewm.dto.stats.exception;

public class NullObjectException extends NullPointerException {
    public NullObjectException(String massage) {
        super(massage);
    }
}