package ru.vostrodymov.grader.v3.api.filter;

import com.querydsl.core.types.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDefinition {
    private String field;
    private Order direction;
}
