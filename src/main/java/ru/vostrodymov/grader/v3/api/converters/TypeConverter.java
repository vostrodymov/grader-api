package ru.vostrodymov.grader.v3.api.converters;

public class TypeConverter<T> {

    public T convert(Object value, Class<?> valueType) {
        return convert(value);
    }

    public T convert(Object value) {
        return (T) value;
    }
}
