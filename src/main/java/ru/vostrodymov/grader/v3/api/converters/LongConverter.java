package ru.vostrodymov.grader.v3.api.converters;

public class LongConverter extends TypeConverter<Long> {

    @Override
    public Long convert(Object value) {
        if (Integer.class.isAssignableFrom(value.getClass())) {
            return ((Integer) value).longValue();
        }
        return Long.valueOf(String.valueOf(value));
    }
}
