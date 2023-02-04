package ru.practicum.ewm.service.exception;

public class ConflictException extends NullPointerException {
    public ConflictException(String massage) {
        super(massage);
    }
}