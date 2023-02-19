package ru.practicum.ewm.exception;

public class ConflictException extends NullPointerException {
    public ConflictException(String massage) {
        super(massage);
    }
}