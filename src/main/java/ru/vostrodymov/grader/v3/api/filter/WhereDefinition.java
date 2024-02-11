package ru.vostrodymov.grader.v3.api.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class WhereDefinition {
    private GroupType type;
    private String field;
    private CompareType compare;
    private Object value;
    private List<WhereDefinition> items;

    public WhereDefinition(final String field, final CompareType compare, final Object value) {
        this.field = field;
        this.compare = compare;
        this.value = value;
    }

    public boolean isGroup() {
        return Optional.ofNullable(items).map(List::size).map(q -> q > 0).orElse(false);
    }
}
