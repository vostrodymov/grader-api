package ru.vostrodymov.grader.v3.api.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends TypeConverter<LocalDate> {
    private static final String PATTERN = "dd.MM.yyyy";

    @Override
    public LocalDate convert(Object value) {
        var strValue = String.valueOf(value);
        return LocalDate.parse(strValue, DateTimeFormatter.ofPattern(PATTERN));
    }
}
