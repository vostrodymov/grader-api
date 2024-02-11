package ru.vostrodymov.grader.v3.api.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryDefinition {
    /**
     * Сортировка
     */
    private OrderDefinition order;
    /**
     * Страница данных
     */
    private PageDefinition page;
    /**
     * Указывает возвращать ли кол-во доступных записей в БД
     */
    private Boolean total = true;
    /**
     * Критерии поиска
     */
    private List<WhereDefinition> where;
}
