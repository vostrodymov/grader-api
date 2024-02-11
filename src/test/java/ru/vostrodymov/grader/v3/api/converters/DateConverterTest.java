package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverterTest {
    private static final String JSON_VALUE = "08.02.2014";
    private static final String PATTERN = "dd.MM.yyyy";

    private static final LocalDate OBJECT_LOCAL_DATE_VALUE = LocalDate
            .parse(JSON_VALUE, DateTimeFormatter.ofPattern(PATTERN));


    @Test
    public void fromLocalDateObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_LOCAL_DATE_VALUE, LocalDate.class);

        Assertions.assertEquals(result, OBJECT_LOCAL_DATE_VALUE);
    }

    @Test
    public void fromJsonToLocalDate() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, LocalDate.class);

        Assertions.assertEquals(result, OBJECT_LOCAL_DATE_VALUE);
    }
}
