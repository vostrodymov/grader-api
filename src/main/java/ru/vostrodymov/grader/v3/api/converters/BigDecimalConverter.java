package ru.vostrodymov.grader.v3.api.converters;

import java.math.BigDecimal;

public class BigDecimalConverter extends TypeConverter<BigDecimal> {

    @Override
    public BigDecimal convert(Object value) {
        if (Double.class.isAssignableFrom(value.getClass())) {
            return BigDecimal.valueOf((Double) value);
        }
        return super.convert(value);
    }
}
