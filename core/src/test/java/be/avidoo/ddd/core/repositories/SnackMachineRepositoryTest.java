package be.avidoo.ddd.core.repositories;

import be.avidoo.ddd.core.CoreTestConfiguration;
import be.avidoo.ddd.core.aggregate.SnackMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CoreTestConfiguration.class)
class SnackMachineRepositoryTest {

    @Autowired
    private SnackMachineRepository snackMachineRepository;

    @Test
    void findById() {
        SnackMachine snackMachine = new SnackMachine(1L);

        SnackMachine save = snackMachineRepository.save(snackMachine);

        assertThat(save.getId()).isEqualTo(1L);
    }
}
