package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.AbstractAggregateRoot;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class SnackMachine extends AbstractAggregateRoot {

    @Embedded
    private Money moneyInside;
    @Transient
    private Money moneyInTransaction;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Slot> slots;

    public SnackMachine() {
        this.moneyInside = MoneyFactory.NONE;
        this.moneyInTransaction = MoneyFactory.NONE;
        this.slots = List.of(
                new Slot(this, 1),
                new Slot(this, 2),
                new Slot(this, 3)
        );
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
        this.moneyInTransaction = this.moneyInTransaction.sum(money);
    }

    public void returnMoney() {
        this.moneyInTransaction = MoneyFactory.NONE;
    }

    public void buySnack(int position) {
        Slot slot = findSlot(position);
        if (slot.getSnackPile().getPrice() > this.moneyInTransaction.amount()) {
            throw new IllegalArgumentException("Not enough money inserted");
        }

        slot.setSnackPile(slot.getSnackPile().subtractOne());
        this.moneyInside = this.moneyInside.sum(this.moneyInTransaction);
        this.moneyInTransaction = MoneyFactory.NONE;
    }


    public void loadSnack(int position, SnackPile snackPile) {
        Slot slot = findSlot(position);
        slot.setSnackPile(snackPile);
    }

    private Slot findSlot(int position) {
        Optional<Slot> first = slots.stream().filter(slot -> slot.getPosition() == position).findFirst();
        return first.orElseThrow(() -> new RuntimeException("No slot found for position: " + position));
    }
}
