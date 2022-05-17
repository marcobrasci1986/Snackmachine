package be.avidoo.ddd;

import lombok.Getter;

@Getter
public class Snack extends AbstractEntity {

    private String name;

    public Snack() {
    }

    public Snack(String name) {
        this.name = name;
    }
}
