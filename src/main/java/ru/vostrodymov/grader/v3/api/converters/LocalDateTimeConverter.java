package ru.vostrodymov.grader.v3.api.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends TypeConverter<LocalDateTime> {
    public static final String PATTERN = "dd.MM.yyyy HH:mm:ss";

    @Override
    public LocalDateTime convert(Object value) {
        var strValue = String.valueOf(value);
        return LocalDateTime.parse(strValue, DateTimeFormatter.ofPattern(PATTERN));
    }
}
