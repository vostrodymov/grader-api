package ru.vostrodymov.grader.v3.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vostrodymov.grader.v3.api.types.GenericEnumerator;

import java.util.Map;

public class EnumConverterTest {
    private static final String VALUE_KEY = "value";

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fromObject() {
        var store = new ConverterStore();

        var result = store.prepareValue(Status.REMOVED, Status.class);

        Assertions.assertEquals(result, Status.REMOVED);
    }

    @Test
    public void fromJsonStr() throws JsonProcessingException {
        var json = "{ \"value\":\"" + Status.ACTIVE + "\" }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get(VALUE_KEY), Status.class);

        Assertions.assertEquals(result, Status.ACTIVE);
    }

    @Test
    public void fromJsonObj() throws JsonProcessingException {
        var json = "{ \"value\":{ \"name\":\"" + Status.ACTIVE + "\", \"label\":\"Действует\"} }";
        var map = mapper.readValue(json, Map.class);

        var store = new ConverterStore();

        var result = store.prepareValue(map.get(VALUE_KEY), Status.class);

        Assertions.assertEquals(result, Status.ACTIVE);
    }

    public enum Status implements GenericEnumerator {
        ACTIVE,
        REMOVED
    }
}
