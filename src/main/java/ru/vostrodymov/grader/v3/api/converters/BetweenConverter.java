package ru.vostrodymov.grader.v3.api.converters;

import ru.vostrodymov.grader.v3.api.types.BetweenValue;

import java.util.Map;

public class BetweenConverter extends TypeConverter<BetweenValue> {
    private static final String FROM_KEY = "from";
    private static final String TO_KEY = "to";

    @Override
    public BetweenValue convert(Object value) {
        if (Map.class.isAssignableFrom(value.getClass())) {
            var map = (Map<String, Object>) value;
            return new BetweenValue(
                    map.get(FROM_KEY),
                    map.get(TO_KEY)
            );
        }
        return super.convert(value);
    }
}
