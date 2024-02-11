package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverterTest {
    private static final String JSON_VALUE = "08.02.2014 10:17:43";
    private static final String PATTERN = "dd.MM.yyyy HH:mm:ss";


    private static final LocalDateTime OBJECT_LOCAL_DATE_TIME_VALUE = LocalDateTime
            .parse(JSON_VALUE, DateTimeFormatter.ofPattern(PATTERN));

    @Test
    public void fromLocalDateObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_LOCAL_DATE_TIME_VALUE, LocalDateTime.class);

        Assertions.assertEquals(result, OBJECT_LOCAL_DATE_TIME_VALUE);
    }

    @Test
    public void fromJsonToLocalDate() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, LocalDateTime.class);

        Assertions.assertEquals(result, OBJECT_LOCAL_DATE_TIME_VALUE);
    }
}
