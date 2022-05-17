package be.avidoo.ddd.aggregate.snack;

import be.avidoo.ddd.AbstractAggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Snack extends AbstractAggregateRoot {

    private String name;

    public Snack(String name) {
        this.name = name;
    }
}
