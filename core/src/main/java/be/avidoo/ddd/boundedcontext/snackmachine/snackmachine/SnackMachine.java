package be.avidoo.ddd.boundedcontext.snackmachine.snackmachine;


import be.avidoo.ddd.common.AggregateRoot;
import be.avidoo.ddd.sharedkernel.Money;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Snackmachine")
@Getter
public class SnackMachine extends AggregateRoot<SnackMachine> {

    @Embedded
    private Money moneyInside;
    @Transient
    private double moneyInTransaction;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_snackmachine")
    private List<Slot> slots;

    public SnackMachine(long id) {
        this();
        this.id = id;
    }

    public SnackMachine() {
        this.moneyInside = Money.NONE;
        this.moneyInTransaction = 0;
        this.slots = new ArrayList<>();

        this.slots.add(new Slot(1));
        this.slots.add(new Slot(2));
        this.slots.add(new Slot(3));
    }

    public SnackPile getSnackPile(int position) {
        Slot slot = findSlot(position);
        return slot.getSnackPile();
    }


    public void insertMoney(Money money) {
        List<Money> possibleValues = List.of(Money.FIFTY_CENT, Money.ONE_EURO, Money.TWO_EURO, Money.FIVE_EURO, Money.TEN_EURO);

        if (!possibleValues.contains(money)) {
            throw new IllegalArgumentException("Cannot insert more than 1 coin or bill at a time. Possibilities are: " + possibleValues);
        }
        this.moneyInTransaction = this.moneyInTransaction + money.amount();
        this.moneyInside = this.moneyInside.sum(money);
    }

    public void returnMoney() {
        Money moneyToReturn = this.moneyInside.allocate(this.moneyInTransaction);
        this.moneyInside = this.moneyInside.subtract(moneyToReturn);
        this.moneyInTransaction = 0;
    }

    public boolean canBuySnack(int position) {
        SnackPile snackPile = getSnackPile(position);

        if (snackPile.getQuantity() == 0)
            throw new IllegalStateException("Quantity is zero");
        if (this.moneyInTransaction < snackPile.getPrice())
            throw new IllegalStateException("Not enough money inserted");
        if (!moneyInside.canAllocate(this.moneyInTransaction - snackPile.getPrice()))
            throw new IllegalStateException("Not enough money in machine for change");

        return true;
    }

    public void buySnack(int position) {
        Slot slot = findSlot(position);
        if (slot.getSnackPile().getPrice() > this.moneyInTransaction) {
            throw new IllegalArgumentException("Not enough money inserted");
        }

        slot.setSnackPile(slot.getSnackPile().subtractOne());

        Money change = this.moneyInside.allocate(moneyInTransaction - slot.getSnackPile().getPrice());
        if (change.amount() < this.moneyInTransaction - slot.getSnackPile().getPrice()) {
            throw new IllegalArgumentException("Cannot return adequate amount of money. Transaction aborted");

        }
        this.moneyInside = this.moneyInside.subtract(change);
        this.moneyInTransaction = 0;
    }


    public void loadSnack(int position, SnackPile snackPile) {
        Slot slot = findSlot(position);
        slot.setSnackPile(snackPile);
    }

    private Slot findSlot(int position) {
        Optional<Slot> first = slots.stream().filter(slot -> slot.getPosition() == position).findFirst();
        return first.orElseThrow(() -> new RuntimeException("No slot found for position: " + position));
    }

    public void loadMoney(Money money) {
        this.moneyInside = this.moneyInside.sum(money);
    }
}
