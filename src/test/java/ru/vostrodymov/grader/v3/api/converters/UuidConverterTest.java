package ru.vostrodymov.grader.v3.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UuidConverterTest {
    private static final UUID OBJECT_VALUE = UUID.randomUUID();
    private static final String JSON_VALUE = OBJECT_VALUE.toString();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, UUID.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() {
        var store = new ConverterStore();

        var result = store.prepareValue(JSON_VALUE, UUID.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

}
