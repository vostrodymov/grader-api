package ru.vostrodymov.grader.v3.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BooleanConverterTest {
    private static final Boolean OBJECT_VALUE = true;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, Boolean.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        var json = "{\"value\": true }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get("value"), Boolean.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }
}
