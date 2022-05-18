package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.aggregate.snack.Snack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnackMachineTest {

    @Test
    void returnMoneyEmptiesMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        snackMachine.returnMoney();

        assertThat((snackMachine.getMoneyInTransaction())).isEqualTo(0);
    }

    @Test
    void insertedMoneyGoesToMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyFactory.FIFTY_CENT);
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        assertThat((snackMachine.getMoneyInTransaction())).isEqualTo(1.5);
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
        snackMachine.loadSnack(1, new SnackPile(Snack.CHOCOLATE, 10, 1));
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        snackMachine.buySnack(1);

        assertThat(snackMachine.getMoneyInside()).isEqualTo(
                new Money(0, 1, 0, 0, 0)
        );
        assertThat(snackMachine.getMoneyInTransaction()).isEqualTo(
                0
        );
        assertThat(snackMachine.getSnackPile(1).getQuantity()).isEqualTo(9);
    }

    @Test
    void cannotBuySnackWhenNoSnacksAreLoaded() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    void cannotBuySnackWhenNotEnoughMoneyInserted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnack(1, new SnackPile(Snack.CHOCOLATE, 10, 2));
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    void returnMoneyWithHighestDenominationFirst() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadMoney(MoneyFactory.ONE_EURO);

        snackMachine.insertMoney(MoneyFactory.FIFTY_CENT);
        snackMachine.insertMoney(MoneyFactory.FIFTY_CENT);

        snackMachine.returnMoney();

        assertThat(snackMachine.getMoneyInside().getFiftyCentCoin()).isEqualTo(2);
        assertThat(snackMachine.getMoneyInside().getOneEuroCoin()).isEqualTo(0);
    }

    @Test
    void returnChangeAfterPurchaseIsCompleted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnack(1, new SnackPile(Snack.CHOCOLATE, 1, 0.5));
        snackMachine.loadMoney(new Money(2, 0, 0, 0, 0));

        snackMachine.insertMoney(MoneyFactory.ONE_EURO);
        snackMachine.buySnack(1);

        assertThat(snackMachine.getMoneyInside()).isEqualTo(new Money(1, 1, 0, 0, 0));
        assertThat(snackMachine.getMoneyInTransaction()).isEqualTo(0);
    }

    @Test
    void cannotBuySnackIfNotEnoughChange() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnack(1, new SnackPile(Snack.CHOCOLATE, 1, 0.5));
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            snackMachine.buySnack(1);
        });
    }
}
