package ru.practicum.ewm.server.stats.exception;

public class NullObjectException extends NullPointerException {
    public NullObjectException(String massage) {
        super(massage);
    }
}