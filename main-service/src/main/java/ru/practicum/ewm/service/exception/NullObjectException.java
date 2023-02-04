package ru.practicum.ewm.service.exception;

public class NullObjectException extends NullPointerException {
    public NullObjectException(String massage) {
        super(massage);
    }
}