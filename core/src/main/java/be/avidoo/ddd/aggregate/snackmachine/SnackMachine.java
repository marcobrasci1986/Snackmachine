package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.AbstractAggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class SnackMachine extends AbstractAggregateRoot {

    @Embedded
    private Money moneyInside;
    @Transient
    private double moneyInTransaction;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Slot> slots;

    public SnackMachine(long id) {
        this();
        this.id = id;
    }

    public SnackMachine() {
        this.moneyInside = MoneyFactory.NONE;
        this.moneyInTransaction = 0;
        this.slots = new ArrayList<>();

        this.slots.add(new Slot(this, 1));
        this.slots.add(new Slot(this, 2));
        this.slots.add(new Slot(this, 3));
    }

    public SnackPile getSnackPile(int position) {
        Slot slot = findSlot(position);
        return slot.getSnackPile();
    }


    public void insertMoney(Money money) {
        List<Money> possibleValues = List.of(MoneyFactory.FIFTY_CENT, MoneyFactory.ONE_EURO, MoneyFactory.TWO_EURO, MoneyFactory.FIVE_EURO, MoneyFactory.TEN_EURO);

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
