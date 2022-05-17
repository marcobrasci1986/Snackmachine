package be.avidoo.ddd;

import be.avidoo.ddd.aggregate.snackmachine.SnackMachine;
import lombok.Getter;

@Getter
public class Slot extends AbstractEntity {

    private Snack snack;
    private int quantity;
    private double price;
    private SnackMachine snackMachine;
    private int position;

    public Slot(Snack snack, int quantity, double price, SnackMachine snackMachine, int position) {
        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
        this.snackMachine = snackMachine;
        this.position = position;
    }
}
