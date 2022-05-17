package be.avidoo.ddd.aggregate.snackmachine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SnackMachineTestConfiguration.class)
class SnackMachineRepositoryTest {

    @Autowired
    private SnackMachineRepository snackMachineRepository;

    @Test
    void findById() {
        SnackMachine snackMachine = new SnackMachine(1L);

        SnackMachine savedSnackMachine = snackMachineRepository.save(snackMachine);

        assertThat(savedSnackMachine.getId()).isEqualTo(1L);
    }

    @Test
    void insertMoneyAndBuySnack() {
        SnackMachine snackMachine = new SnackMachine(1L);
        snackMachine.insertMoney(MoneyFactory.ONE_EURO);
        snackMachine.insertMoney(MoneyFactory.FIFTY_CENT);
        snackMachine.buySnack();

        SnackMachine savedSnackMachine = snackMachineRepository.save(snackMachine);

        assertThat(savedSnackMachine.getId()).isEqualTo(1L);
        assertThat(savedSnackMachine.getMoneyInside().amount()).isEqualTo(1.5);
    }
}
