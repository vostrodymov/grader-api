package ru.vostrodymov.grader.v3.api.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetweenValue {
    private Object from;
    private Object to;
}
