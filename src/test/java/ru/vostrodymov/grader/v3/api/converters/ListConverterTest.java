package ru.vostrodymov.grader.v3.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ListConverterTest {
    private static final Object OBJECT_ONE_VALUE = 123;
    private static final Object OBJECT_TWO_VALUE = 987;
    private static final List OBJECT_VALUE = List.of(OBJECT_ONE_VALUE, OBJECT_TWO_VALUE);

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, List.class);

        Assertions.assertEquals(result.get(0), OBJECT_ONE_VALUE);
        Assertions.assertEquals(result.get(1), OBJECT_TWO_VALUE);
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        var json = "{ \"value\":[" + OBJECT_ONE_VALUE + ", " + OBJECT_TWO_VALUE + "] }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get("value"), List.class);

        Assertions.assertEquals(result.get(0), OBJECT_ONE_VALUE);
        Assertions.assertEquals(result.get(1), OBJECT_TWO_VALUE);
    }
}
