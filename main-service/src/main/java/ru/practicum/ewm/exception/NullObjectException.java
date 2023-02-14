package ru.practicum.ewm.exception;

public class NullObjectException extends NullPointerException {
    public NullObjectException(String massage) {
        super(massage);
    }
}