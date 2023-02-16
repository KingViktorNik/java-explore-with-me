package ru.practicum.ewm.server.stats.exception;

public class NotFoundException extends NullPointerException {
    public NotFoundException(String massage) {
        super(massage);
    }
}