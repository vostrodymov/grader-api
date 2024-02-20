package ru.vostrodymov.grader.v3.api.query;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import ru.vostrodymov.grader.v3.api.converters.ConverterStore;
import ru.vostrodymov.grader.v3.api.filter.*;
import ru.vostrodymov.grader.v3.api.types.SortableEnumerator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Базовый класс для настройки фильтра поиска для заданной сущности
 */
@Getter
public abstract class BaseQuery<T> extends BaseQueryBuilder {
    private final EntityManager entityManager;

    public BaseQuery(ConverterStore converterStore, EntityManager entityManager) {
        super(converterStore);
        this.entityManager = entityManager;
    }

    public List<T> find(QueryDefinition query) {
        var jpa = takeQueryFactory(query)
                .where(build(query.getWhere()));
        Optional.of(query).map(QueryDefinition::getPage)
                .ifPresent(q -> {
                    Optional.of(q).map(PageDefinition::getSize).ifPresent(jpa::limit);
                    Optional.of(q).map(PageDefinition::getNumber).ifPresent(jpa::offset);
                });
        Optional.of(query).map(QueryDefinition::getOrder)
                .map(this::toOrderExpression)
                .ifPresent(jpa::orderBy);
        return jpa.fetch();
    }

    public T findSingle(QueryDefinition query) {
        var jpa = takeQueryFactory(query)
                .where(build(query.getWhere()))
                .limit(1)
                .offset(0);
        return jpa.fetchOne();
    }

    public Long count(QueryDefinition query) {
        var jpa = new JPAQueryFactory(entityManager)
                .from(getEntityPath())
                .select(getEntityPath().count())
                .where(build(query.getWhere()));
        return jpa.fetchFirst();
    }

    public void save(T item) {
        getEntityManager().persist(item);
        getEntityManager().flush();
    }

    public BooleanExpression[] build(List<WhereDefinition> whereList) {
        return new BooleanExpression[]{doBuild(whereList)};
    }

    protected BooleanExpression doBuild(List<WhereDefinition> whereList) {
        BooleanExpression expression = Expressions.ONE.eq(Expressions.ONE);

        var expressions = prepare(whereList);
        BooleanExpression[] arr = expressions.toArray(BooleanExpression[]::new);
        expression = Expressions.allOf(arr);

        return expression;
    }

    private List<BooleanExpression> prepare(List<WhereDefinition> whereList) {
        var result = new ArrayList<BooleanExpression>();
        for (var where : whereList) {
            if (where.isGroup()) {
                var items = prepare(where.getItems()).toArray(BooleanExpression[]::new);
                if (where.getType().equals(GroupType.AND)) {
                    result.add(Expressions.allOf(items));
                } else {
                    result.add(Expressions.anyOf(items));
                }
            } else {
                result.add(toExpression(where));
            }
        }
        return result;
    }

    protected abstract BooleanExpression toExpression(WhereDefinition where);

    protected abstract OrderSpecifier<?> toOrderExpression(OrderDefinition order);

    /**
     * Метод построения case для верной сортировки по локализованному значению
     */
    protected <R extends Enum<R> & SortableEnumerator> StringExpression orderCase(EnumPath<R> field,
                                                                                  Class<R> enumClass) {
        var values = enumClass.getEnumConstants();
        CaseBuilder.Cases<String, StringExpression> casesValue = null;
        for (var el : values) {
            var val = (SortableEnumerator) el;
            casesValue = casesValue != null
                    ? casesValue.when(field.eq(el)).then(val.getLabel())
                    : Expressions.cases().when(field.eq(el)).then(val.getLabel());
        }
        return casesValue.otherwise("");
    }

    protected JPAQuery<T> takeQueryFactory(QueryDefinition query) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(getEntityPath());
    }

    protected abstract EntityPathBase<T> getEntityPath();
}
