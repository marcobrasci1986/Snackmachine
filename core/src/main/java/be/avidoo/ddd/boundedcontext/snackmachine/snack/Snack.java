package be.avidoo.ddd.boundedcontext.snackmachine.snack;

import be.avidoo.ddd.common.AggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Snack")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Snack extends AggregateRoot<Snack> {
    public static final Snack NONE = new Snack(0, "None");
    public static final Snack CHOCOLATE = new Snack(1, "Chocolate");
    public static final Snack GUM = new Snack(2, "Gum");
    public static final Snack SODA = new Snack(3, "Soda");


    @Column(name = "name")
    private String name;

    public Snack(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
