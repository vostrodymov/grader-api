package ru.vostrodymov.grader.v3.api.converters;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConverter extends TypeConverter<OffsetDateTime> {

    @Override
    public OffsetDateTime convert(Object value) {
        if (OffsetDateTime.class.isAssignableFrom(value.getClass())) {
            return (OffsetDateTime) value;
        } else {
            var ldt = LocalDateTime.parse(String.valueOf(value), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            return OffsetDateTime.of(ldt, ZoneOffset.UTC);
        }
    }
}
