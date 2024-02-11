package ru.vostrodymov.grader.v3.api.converters;

import ru.vostrodymov.grader.v3.api.types.GenericEnumerator;
import ru.vostrodymov.grader.v3.api.types.BetweenValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConverterStore {
    private final Map<Class<?>, TypeConverter<?>> items = new HashMap<>();

    public ConverterStore() {
        addConverter(UUID.class, new UniqueIdentifierTypeConverter());
        addConverter(OffsetDateTime.class, new OffsetDateTimeConverter());
        addConverter(LocalDate.class, new LocalDateConverter());
        addConverter(LocalDateTime.class, new LocalDateTimeConverter());
        addConverter(Long.class, new LongConverter());
        addConverter(BigDecimal.class, new BigDecimalConverter());
        addConverter(BetweenValue.class, new BetweenConverter());
        addConverter(GenericEnumerator.class, new GenericEnumeratorConvertor());
    }

    public <R> R prepareValue(Object value, Class<R> clazz) {
        if (value == null) {
            return null;
        }
        R result = null;
        if (clazz.isAssignableFrom(value.getClass())) {
            result = (R) value;
        } else {
            for (var item : items.entrySet()) {
                if (item.getKey().isAssignableFrom(clazz)) {
                    result = (R) item.getValue().convert(value, clazz);
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * Добавление нового конвертера
     *
     * @param clazz     Класс который необходимо обрабатывать данным конвертором
     * @param converter Конвертер
     */
    public void addConverter(Class<?> clazz, TypeConverter converter) {
        this.items.put(clazz, converter);
    }
}
