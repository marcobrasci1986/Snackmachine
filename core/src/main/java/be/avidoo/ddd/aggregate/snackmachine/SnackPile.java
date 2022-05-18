package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.ValueObject;
import be.avidoo.ddd.aggregate.snack.Snack;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
class SnackPile extends ValueObject {

    public static SnackPile EMPTY = new SnackPile(Snack.NONE, 0, 0);
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_snack")
    private Snack snack;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    public SnackPile(Snack snack, int quantity, double price) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (price < 0)
            throw new IllegalArgumentException();
        if (price % 0.5 > 0)
            throw new IllegalArgumentException();

        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }

    public SnackPile subtractOne() {
        return new SnackPile(
                snack,
                quantity - 1,
                price
        );
    }
}
