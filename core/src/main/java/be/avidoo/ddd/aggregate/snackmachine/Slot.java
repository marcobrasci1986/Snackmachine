package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Slot extends AbstractEntity {

    private SnackPile snackPile;
    private SnackMachine snackMachine;
    private int position;

    public Slot(SnackMachine snackMachine, int position) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snackPile = new SnackPile(null, 0, 0);
    }

    public void setSnackPile(SnackPile snackPile) {
        this.snackPile = snackPile;
    }
}
