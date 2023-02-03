package ru.practicum.ewm.dto.stats.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String massage) {
        super(massage);
    }
}