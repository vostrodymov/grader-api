package ru.vostrodymov.grader.v3.api.service;

import ru.vostrodymov.grader.v3.api.filter.QueryDefinition;

import java.util.List;

/**
 * Базовый класс для построения сервиса
 */
public abstract class RepositoryService<T> {

    public abstract List<T> find(QueryDefinition query);

}
