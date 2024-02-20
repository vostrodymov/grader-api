package ru.vostrodymov.grader.v3.api.types;

/**
 * Интерфейс для указания что перечисление должно быть отсортированно по заголовку
 */
public interface SortableEnumerator extends GenericEnumerator {

    String getLabel();
}
