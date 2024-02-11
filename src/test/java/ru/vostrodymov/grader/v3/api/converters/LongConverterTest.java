package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongConverterTest {
    private static final Long OBJECT_VALUE = 100500L;
    private static final Integer JSON_VALUE = OBJECT_VALUE.intValue();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, Long.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, Long.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }
}
