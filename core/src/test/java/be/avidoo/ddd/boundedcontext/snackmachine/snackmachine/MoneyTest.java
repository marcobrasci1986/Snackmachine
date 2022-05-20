package be.avidoo.ddd.boundedcontext.snackmachine.snackmachine;

import be.avidoo.ddd.sharedkernel.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void sum() {
        Money money1 = new Money(1, 2, 3, 4, 5);
        Money money2 = new Money(1, 2, 3, 4, 5);

        Money sum = money1.sum(money2);

        Money expectedSum = Money.builder()
                .fiftyCentCoin(2)
                .oneEuroCoin(4)
                .twoEuroCoin(6)
                .fiveEuroBill(8)
                .tenEuroBill(10)
                .build();
        assertThat(sum).isEqualTo(expectedSum);
    }

    @Test
    void equality() {
        Money money1 = new Money(1, 2, 3, 4, 5);
        Money money2 = new Money(1, 2, 3, 4, 5);

        assertThat(money1).isEqualTo(money2);
    }

    @Test
    void hashcode() {
        Money money1 = new Money(1, 2, 3, 4, 5);
        Money money2 = new Money(1, 2, 3, 4, 5);

        assertThat(money1.hashCode()).isEqualTo(money2.hashCode());
    }

    @Test
    void differentObjectsAreNotEqual() {
        Money money1 = new Money(2, 0, 0, 0, 0);
        Money money2 = new Money(0, 1, 0, 0, 0);

        assertThat(money1).isNotEqualTo(money2);
    }

    @Test
    void differentObjectsHaveDifferentHashCodes() {
        Money money1 = new Money(2, 0, 0, 0, 0);
        Money money2 = new Money(0, 1, 0, 0, 0);

        assertThat(money1.hashCode()).isNotEqualTo(money2.hashCode());
    }

    @ParameterizedTest
    @CsvSource({"-1,0,0,0,0", "0,-1,0,0,0", "0,0,-1,0,0", "0,0,0,-1,0", "0,0,0,0,-1"})
    void cannotCreateMoneyWithNegativeValue(
            int fiftyCentCoin, int oneEuroCoin, int twoEuroCoin, int fiveEuroBill, int tenEuroBill) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Money(fiftyCentCoin, oneEuroCoin, twoEuroCoin, fiveEuroBill, tenEuroBill);
        });
    }

    @ParameterizedTest
    @CsvSource({"0,0,0,0,0,0", "1,0,0,0,0,0.5", "1,1,0,0,0,1.5", "1,1,1,0,0,3.5", "1,1,1,1,0,8.5", "1,1,1,1,1,18.5"})
    void calculateAmount(
            int fiftyCentCoin, int oneEuroCoin, int twoEuroCoin, int fiveEuroBill, int tenEuroBill, double expected) {

        Money money = new Money(fiftyCentCoin, oneEuroCoin, twoEuroCoin, fiveEuroBill, tenEuroBill);

        assertThat(money.amount()).isEqualTo(expected);
    }

    @Test
    void subtract() {

        Money money1 = new Money(10, 10, 10, 10, 10);
        Money money2 = new Money(1, 2, 3, 4, 5);

        Money result = money1.subtract(money2);

        assertThat(result).isEqualTo(new Money(9, 8, 7, 6, 5));
    }

    @Test
    void cannotSubtractMoreThanAvailable() {

        Money money1 = new Money(0, 1, 0, 0, 0);
        Money money2 = new Money(1, 0, 0, 0, 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            money1.subtract(money2);
        });
    }

    @Test
    void allocateMoneyInFiftyCents() {
        Money moneyInside = new Money(2, 0, 0, 0, 0);

        Money moneyToReturn = moneyInside.allocate(1);

        assertThat(moneyToReturn).isEqualTo(
                new Money(2, 0, 0, 0, 0)
        );
    }

    @Test
    void allocateMoneyInOneEuros() {
        Money moneyInside = new Money(2, 1, 0, 0, 0);

        Money moneyToReturn = moneyInside.allocate(1);

        assertThat(moneyToReturn).isEqualTo(
                new Money(0, 1, 0, 0, 0)
        );
    }

    @Test
    void allocateMoneyInTwoEuros() {
        Money moneyInside = new Money(2, 2, 1, 0, 0);

        Money moneyToReturn = moneyInside.allocate(2.5);

        assertThat(moneyToReturn).isEqualTo(
                new Money(1, 0, 1, 0, 0)
        );
    }

    @Test
    void allocateMoneyInFiveEuros() {
        Money moneyInside = new Money(2, 2, 2, 1, 0);

        Money moneyToReturn = moneyInside.allocate(6.5);

        assertThat(moneyToReturn).isEqualTo(
                new Money(1, 1, 0, 1, 0)
        );
    }

    @Test
    void allocateMoneyInTenEuros() {
        Money moneyInside = new Money(2, 10, 5, 2, 1);

        Money moneyToReturn = moneyInside.allocate(11.5);

        assertThat(moneyToReturn).isEqualTo(
                new Money(1, 1, 0, 0, 1)
        );
    }

    @Test
    void allocateMoneyNotPossible() {
        Money moneyInside = new Money(0, 0, 0, 0, 0);


        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            moneyInside.allocate(1);
        });

        assertThat(ex.getMessage()).isEqualTo("Cannot allocate change");

    }
}
