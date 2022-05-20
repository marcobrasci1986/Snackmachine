package be.avidoo.ddd.boundedcontext.atm;

import be.avidoo.ddd.sharedkernel.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AtmTest {

    @Test
    void takeMoneyWithCommission() {
        Atm atm = new Atm();
        atm.loadMoney(Money.ONE_EURO);

        atm.takeMoney(1D);

        assertThat(atm.getMoneyInside().amount()).isEqualTo(0);
        assertThat(atm.getMoneyCharged()).isEqualTo(1.01);
    }
}
