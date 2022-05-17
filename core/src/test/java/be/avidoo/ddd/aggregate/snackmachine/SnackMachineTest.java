package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.aggregate.snackmachine.Money;
import be.avidoo.ddd.aggregate.snackmachine.MoneyFactory;
import be.avidoo.ddd.aggregate.snackmachine.SnackMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnackMachineTest {

    @Test
    void returnMoneyEmptiesMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        snackMachine.returnMoney();

        assertThat((snackMachine.getMoneyInTransaction().amount())).isEqualTo(0);
    }

    @Test
    void insertedMoneyGoesToMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyFactory.FIFTY_CENT);
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        assertThat((snackMachine.getMoneyInTransaction().amount())).isEqualTo(1.5);
    }

    @Test
    void cannotInsertMoreThanOneCoinOrBillAtATime() {
        SnackMachine snackMachine = new SnackMachine();

        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            snackMachine.insertMoney(new Money(1, 1, 0, 0, 0));
        });

        assertThat(ex.getMessage()).contains("Cannot insert more than 1 coin or bill at a time");
    }

    @Test
    void buySnack() {
        SnackMachine snackMachine = new SnackMachine();
//        snackMachine.loadSnack();

        snackMachine.insertMoney(MoneyFactory.ONE_EURO);
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        snackMachine.buySnack();

        assertThat(snackMachine.getMoneyInside()).isEqualTo(
                new Money(0, 2, 0, 0, 0)
        );
        assertThat(snackMachine.getMoneyInTransaction()).isEqualTo(
                MoneyFactory.NONE
        );
    }
}
