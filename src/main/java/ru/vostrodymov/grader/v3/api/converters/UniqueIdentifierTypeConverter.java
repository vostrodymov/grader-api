package ru.vostrodymov.grader.v3.api.converters;

import java.util.UUID;

/**
 * Конвертер UUID
 * с фронта приходит в строке, а с бэка приходит как UUID
 */
public class UniqueIdentifierTypeConverter extends TypeConverter<UUID> {

    @Override
    public UUID convert(Object value) {
        return UUID.fromString(String.valueOf(value));
    }
}
