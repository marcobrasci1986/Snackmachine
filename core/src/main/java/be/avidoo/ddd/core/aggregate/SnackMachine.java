package be.avidoo.ddd.core.aggregate;

import be.avidoo.ddd.core.AbstractEntity;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Getter
public class SnackMachine extends AbstractEntity {

    @Embedded
    private Money moneyInside = MoneyFactory.NONE;
    @Transient
    private Money moneyInTransaction = MoneyFactory.NONE;

    public SnackMachine() {
    }

    public SnackMachine(long id) {
        super(id);
    }

    public void insertMoney(Money money) {
        List<Money> possibleValues = List.of(MoneyFactory.FIFTY_CENT, MoneyFactory.ONE_EURO, MoneyFactory.TWO_EURO,
                MoneyFactory.FIVE_EURO, MoneyFactory.TEN_EURO);

        if (!possibleValues.contains(money)) {
            throw new IllegalArgumentException("Cannot insert more than 1 coin or bill at a time. Possibilities are: " + possibleValues);
        }
        this.moneyInTransaction = this.moneyInTransaction.sum(money);
    }

    public void returnMoney() {
        this.moneyInTransaction = MoneyFactory.NONE;
    }

    public void buySnack() {
        this.moneyInside = this.moneyInside.sum(this.moneyInTransaction);
        this.moneyInTransaction = MoneyFactory.NONE;
    }
}
