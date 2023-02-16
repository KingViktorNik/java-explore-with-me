package ru.practicum.ewm.exception;

public class NotFoundException extends NullPointerException {
    public NotFoundException(String massage) {
        super(massage);
    }
}