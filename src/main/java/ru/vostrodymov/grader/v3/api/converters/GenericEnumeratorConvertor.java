package ru.vostrodymov.grader.v3.api.converters;

import ru.vostrodymov.grader.v3.api.types.GenericEnumerator;

import java.util.Arrays;
import java.util.Map;

public class GenericEnumeratorConvertor extends TypeConverter<GenericEnumerator> {
    @Override
    public GenericEnumerator convert(Object value, Class<?> enumClass) {
        String strValue = String.valueOf(value);
        if (String.class.isAssignableFrom(value.getClass())) {
            strValue = (String) value;
        } else if (Map.class.isAssignableFrom(value.getClass())) {
            Map<String, Object> mapValue = (Map<String, Object>) value;
            strValue = (String) mapValue.get("name");
        }
        final String eqValue = strValue;
        return Arrays.stream(((Enum[]) enumClass.getEnumConstants())).filter(q -> q.name().equals(eqValue))
                .findFirst()
                .map(q -> (GenericEnumerator) q)
                .orElse(null);
    }
}
