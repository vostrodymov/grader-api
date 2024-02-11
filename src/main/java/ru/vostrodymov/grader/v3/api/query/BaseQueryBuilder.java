package ru.vostrodymov.grader.v3.api.query;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import ru.vostrodymov.grader.v3.api.converters.ConverterStore;
import ru.vostrodymov.grader.v3.api.filter.CompareType;
import ru.vostrodymov.grader.v3.api.types.BetweenValue;

import java.util.List;

public class BaseQueryBuilder {
    private static final String ERROR_TEXT = "Такой тип сравнения не поддерживается";
    private final ConverterStore converterStore;

    public BaseQueryBuilder(ConverterStore converterStore) {
        this.converterStore = converterStore;
    }

    public ConverterStore getConverterStore() {
        return converterStore;
    }

    @SuppressWarnings("CyclomaticComplexity")
    protected <R extends Comparable<?>> BooleanExpression takeComparableExpression(CompareType compare,
                                                                                   ComparableExpression<R> expression,
                                                                                   Object value,
                                                                                   Class<R> clazz) {
        return switch (compare) {
            case EQUAL -> fillEqual(expression, value, clazz);
            case NOT_EQUAL -> fillNotEqual(expression, value, clazz);
            case IN -> fillIn(expression, value, clazz);
            case NOT_IN -> fillNotIn(expression, value, clazz);
            case EMPTY -> fillEmpty(expression, value, clazz);
            case NOT_EMPTY -> fillNotEmpty(expression, value, clazz);
            case LOWER -> fillLower(expression, value, clazz);
            case LOWER_OR_EQUAL -> fillLowerOrEqual(expression, value, clazz);
            case GREATER -> fillGreater(expression, value, clazz);
            case GREATER_OR_EQUAL -> fillGreaterOrEqual(expression, value, clazz);
            case BETWEEN -> fillBetween(expression, value, clazz);
            default -> throw new RuntimeException(ERROR_TEXT);
        };
    }

    @SuppressWarnings("CyclomaticComplexity")
    protected <R extends Number & Comparable<?>> BooleanExpression takeComparableExpression(
            CompareType compare,
            NumberExpression<R> expression,
            Object value,
            Class<R> clazz) {
        return switch (compare) {
            case EQUAL -> fillEqual(expression, value, clazz);
            case NOT_EQUAL -> fillNotEqual(expression, value, clazz);
            case IN -> fillIn(expression, value, clazz);
            case NOT_IN -> fillNotIn(expression, value, clazz);
            case EMPTY -> fillEmpty(expression, value, clazz);
            case NOT_EMPTY -> fillNotEmpty(expression, value, clazz);
            case LOWER -> fillLower(expression, value, clazz);
            case LOWER_OR_EQUAL -> fillLowerOrEqual(expression, value, clazz);
            case GREATER -> fillGreater(expression, value, clazz);
            case GREATER_OR_EQUAL -> fillGreaterOrEqual(expression, value, clazz);
            case BETWEEN -> fillBetween(expression, value, clazz);
            default -> throw new RuntimeException(ERROR_TEXT);
        };
    }

    @SuppressWarnings("CyclomaticComplexity")
    protected <R extends Comparable<?>> BooleanExpression takeStringExpression(CompareType compare,
                                                                               StringPath expression,
                                                                               Object value,
                                                                               Class<R> clazz) {
        return switch (compare) {
            case EQUAL -> fillEqual((SimpleExpression<R>) expression, value, clazz);
            case NOT_EQUAL -> fillNotEqual((SimpleExpression<R>) expression, value, clazz);
            case IN -> fillIn((SimpleExpression<R>) expression, value, clazz);
            case NOT_IN -> fillNotIn((SimpleExpression<R>) expression, value, clazz);
            case EMPTY -> fillEmpty((SimpleExpression<R>) expression, value, clazz);
            case NOT_EMPTY -> fillNotEmpty((SimpleExpression<R>) expression, value, clazz);
            case LIKE -> fillLike(expression, value, clazz);
            case NOT_LIKE -> fillNotLike(expression, value, clazz);
            case CONTAINS -> fillContains(expression, value, clazz);
            case START_WITH -> fillStartWith(expression, value, clazz);
            default -> throw new RuntimeException(ERROR_TEXT);
        };
    }

    protected <R extends Comparable<?>> BooleanExpression fillEqual(SimpleExpression<R> expression,
                                                                    Object value,
                                                                    Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.eq(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillNotEqual(SimpleExpression<R> expression,
                                                                       Object value,
                                                                       Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.ne(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillIn(SimpleExpression<R> expression,
                                                                 Object value,
                                                                 Class<R> clazz) {
        var val = converterStore.prepareValue(value, List.class);
        var tVals = val.stream()
                .map(q -> converterStore.prepareValue(q, clazz))
                .toList();
        return expression.in(tVals);
    }

    protected <R extends Comparable<?>> BooleanExpression fillNotIn(SimpleExpression<R> expression,
                                                                    Object value,
                                                                    Class<R> clazz) {
        var val = converterStore.prepareValue(value, List.class);
        var tVals = val.stream()
                .map(q -> converterStore.prepareValue(q, clazz))
                .toList();
        return expression.notIn(tVals);
    }

    protected <R extends Comparable<?>> BooleanExpression fillEmpty(SimpleExpression<R> expression,
                                                                    Object value,
                                                                    Class<R> clazz) {
        return expression.isNull();
    }

    protected <R extends Comparable<?>> BooleanExpression fillNotEmpty(SimpleExpression<R> expression,
                                                                       Object value,
                                                                       Class<R> clazz) {
        return expression.isNotNull();
    }

    protected <R extends Comparable<?>> BooleanExpression fillLower(ComparableExpression<R> expression,
                                                                    Object value,
                                                                    Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.lt(val);
    }

    protected <R extends Number & Comparable<?>> BooleanExpression fillLower(NumberExpression<R> expression,
                                                                             Object value,
                                                                             Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.lt(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillLowerOrEqual(ComparableExpression<R> expression,
                                                                           Object value,
                                                                           Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.loe(val);
    }

    protected <R extends Number & Comparable<?>> BooleanExpression fillLowerOrEqual(NumberExpression<R> expression,
                                                                                    Object value,
                                                                                    Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.loe(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillGreater(ComparableExpression<R> expression,
                                                                      Object value,
                                                                      Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.gt(val);
    }

    protected <R extends Number & Comparable<?>> BooleanExpression fillGreater(NumberExpression<R> expression,
                                                                               Object value,
                                                                               Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.gt(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillGreaterOrEqual(ComparableExpression<R> expression,
                                                                             Object value,
                                                                             Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.goe(val);
    }

    protected <R extends Number & Comparable<?>> BooleanExpression fillGreaterOrEqual(NumberExpression<R> expression,
                                                                                      Object value,
                                                                                      Class<R> clazz) {
        var val = converterStore.prepareValue(value, clazz);
        return expression.goe(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillBetween(ComparableExpression<R> expression,
                                                                      Object value,
                                                                      Class<R> clazz) {
        var betweenVal = converterStore.prepareValue(value, BetweenValue.class);
        var from = converterStore.prepareValue(betweenVal.getFrom(), clazz);
        var to = converterStore.prepareValue(betweenVal.getTo(), clazz);
        return expression.between(from, to);
    }

    protected <R extends Number & Comparable<?>> BooleanExpression fillBetween(NumberExpression<R> expression,
                                                                               Object value,
                                                                               Class<R> clazz) {
        var betweenVal = converterStore.prepareValue(value, BetweenValue.class);
        var from = converterStore.prepareValue(betweenVal.getFrom(), clazz);
        var to = converterStore.prepareValue(betweenVal.getTo(), clazz);
        return expression.between(from, to);
    }

    protected <R extends Comparable<?>> BooleanExpression fillLike(StringPath expression,
                                                                   Object value,
                                                                   Class<R> clazz) {
        var val = converterStore.prepareValue(value, String.class);
        return expression.like(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillNotLike(StringPath expression,
                                                                      Object value,
                                                                      Class<R> clazz) {
        var val = converterStore.prepareValue(value, String.class);
        return expression.notLike(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillContains(StringPath expression,
                                                                       Object value,
                                                                       Class<R> clazz) {
        var val = converterStore.prepareValue(value, String.class);
        return expression.contains(val);
    }

    protected <R extends Comparable<?>> BooleanExpression fillStartWith(StringPath expression,
                                                                        Object value,
                                                                        Class<R> clazz) {
        var val = converterStore.prepareValue(value, String.class);
        return expression.startsWith(val);
    }

    /**
     * Строит сортировку для поля
     *
     * @param expressionBase Поле
     * @param order          направление сортировки
     */
    protected OrderSpecifier<?> toOrderSpecifier(ComparableExpressionBase<?> expressionBase, Order order) {
        if (order != null && order.equals(Order.DESC)) {
            return expressionBase.desc();
        } else {
            return expressionBase.asc();
        }
    }
}
