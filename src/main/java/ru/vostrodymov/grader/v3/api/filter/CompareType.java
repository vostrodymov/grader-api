package ru.vostrodymov.grader.v3.api.filter;

public enum CompareType {
    /**
     * Равно
     */
    EQUAL,
    /**
     * Не равно
     */
    NOT_EQUAL,
    /**
     * Меньше
     */
    LOWER,
    /**
     * Больше
     */
    GREATER,
    /**
     * Меньше равно
     */
    LOWER_OR_EQUAL,
    /**
     * Больше равно
     */
    GREATER_OR_EQUAL,
    /**
     * Содржит
     */
    LIKE,
    /**
     * Включает
     */
    CONTAINS,
    /**
     * Не содржит
     */
    NOT_LIKE,
    /**
     * Включает
     */
    IN,
    /**
     * Не включает
     */
    NOT_IN,
    /**
     * Между
     */
    BETWEEN,
    /**
     * Пустое занчение
     */
    EMPTY,
    /**
     * Не пустое значение
     */
    NOT_EMPTY,
    /**
     * Начинается с
     */
    START_WITH
}
