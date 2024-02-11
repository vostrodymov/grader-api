package ru.vostrodymov.grader.v3.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vostrodymov.grader.v3.api.types.BetweenValue;

import java.util.Map;

public class BetweenConverterTest {
    private static final Integer OBJECT_FROM_VALUE = 123;
    private static final Integer OBJECT_TO_VALUE = 987;
    private static final BetweenValue OBJECT_VALUE = new BetweenValue(OBJECT_FROM_VALUE, OBJECT_TO_VALUE);

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, BetweenValue.class);

        Assertions.assertEquals(result.getFrom(), OBJECT_FROM_VALUE);
        Assertions.assertEquals(result.getTo(), OBJECT_TO_VALUE);
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        var json = "{\"value\": { \"from\":" + OBJECT_FROM_VALUE + ", \"to\":" + OBJECT_TO_VALUE + "} }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get("value"), BetweenValue.class);

        Assertions.assertEquals(result.getFrom(), OBJECT_FROM_VALUE);
        Assertions.assertEquals(result.getTo(), OBJECT_TO_VALUE);
    }
}
