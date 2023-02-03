package ru.practicum.ewm.dto.stats.exception;

public class ConflictException extends NullPointerException {
    public ConflictException(String massage) {
        super(massage);
    }
}