package ru.vostrodymov.grader.v3.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalConverterTest {

    private static final BigDecimal OBJECT_VALUE = BigDecimal.valueOf(100500.0123);
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(OBJECT_VALUE, BigDecimal.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        var json = "{\"value\": " + OBJECT_VALUE + " }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get("value"), BigDecimal.class);

        Assertions.assertEquals(result, OBJECT_VALUE);
    }
}
