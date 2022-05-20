package be.avidoo.ddd.boundedcontext.snackmachine.snackmachine;

import be.avidoo.ddd.boundedcontext.snackmachine.snack.Snack;
import be.avidoo.ddd.boundedcontext.snackmachine.snack.SnackRepository;
import be.avidoo.ddd.sharedkernel.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SnackMachineTestConfiguration.class)
class SnackMachineRepositoryTest {

    @Autowired
    private SnackMachineRepository snackMachineRepository;

    @Autowired
    private SnackRepository snackRepository;

    @BeforeEach
    void setUp() {
        snackRepository.save(Snack.CHOCOLATE);
        snackRepository.save(Snack.GUM);
        snackRepository.save(Snack.SODA);

        SnackMachine snackMachine = new SnackMachine(1);
        snackMachine.loadSnack(1, new SnackPile(Snack.CHOCOLATE, 10, 1.5));
        snackMachine.loadSnack(2, new SnackPile(Snack.GUM, 10, 1.5));
        snackMachine.loadSnack(3, new SnackPile(Snack.SODA, 10, 1.5));

        snackMachineRepository.save(snackMachine);
    }

    @Test
    void findById() {
        Optional<SnackMachine> optionalSnackMachine = snackMachineRepository.findById(1L);

        assertThat(optionalSnackMachine).isPresent();

        optionalSnackMachine.ifPresent(snackMachine -> {
            assertThat(snackMachine.getId()).isNotNull();
            assertThat(snackMachine.getSlots().size()).isEqualTo(3);

            assertThat(snackMachine.getSnackPile(1).getSnack()).isEqualTo(Snack.CHOCOLATE);
            assertThat(snackMachine.getSnackPile(2).getSnack()).isEqualTo(Snack.GUM);
            assertThat(snackMachine.getSnackPile(3).getSnack()).isEqualTo(Snack.SODA);
        });
    }

    @Test
    void buySnack() {
        Optional<SnackMachine> optionalSnackMachine = snackMachineRepository.findById(1L);

        assertThat(optionalSnackMachine).isPresent();

        optionalSnackMachine.ifPresent(snackMachine -> {
            snackMachine.loadMoney(new Money(1, 0, 0, 0, 0));
            snackMachine.insertMoney(Money.ONE_EURO);
            snackMachine.insertMoney(Money.ONE_EURO);

            snackMachine.buySnack(1);

            SnackMachine savedSnackMachine = snackMachineRepository.save(snackMachine);

            assertThat(savedSnackMachine.getMoneyInside()).isEqualTo(new Money(0, 2, 0, 0, 0));
            assertThat(savedSnackMachine.getMoneyInTransaction()).isEqualTo(0);

            assertThat(savedSnackMachine.getSnackPile(1).getQuantity()).isEqualTo(9);

        });
    }
}
