package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringConverterTest {
    private static final String OBJECT_VALUE = "CallString";
    private static final String JSON_VALUE = OBJECT_VALUE.toString();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, String.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, String.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

}
