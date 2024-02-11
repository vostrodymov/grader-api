package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerConverterTest {
    private static final Integer OBJECT_VALUE = 100500;
    private static final Integer JSON_VALUE = OBJECT_VALUE;

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, Integer.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, Integer.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }
}
