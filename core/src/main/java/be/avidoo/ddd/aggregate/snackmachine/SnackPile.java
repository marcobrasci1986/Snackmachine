package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.ValueObject;
import be.avidoo.ddd.aggregate.snack.Snack;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
class SnackPile extends ValueObject {

    private Snack snack;
    private int quantity;
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
