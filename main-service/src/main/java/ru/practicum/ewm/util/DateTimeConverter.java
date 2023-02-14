package ru.practicum.ewm.util;


import ru.practicum.ewm.exception.ValidationException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.net.URLDecoder.decode;

public class DateTimeConverter {
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime toDateTime(String dateTime) {
        try {
            return  LocalDateTime.parse(decode(dateTime, StandardCharsets.UTF_8), DTF);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid dateTime format");
        }
    }

    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(DTF);
    }
}
