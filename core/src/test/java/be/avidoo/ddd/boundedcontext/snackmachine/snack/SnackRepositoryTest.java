package be.avidoo.ddd.boundedcontext.snackmachine.snack;

import be.avidoo.ddd.boundedcontext.AbstractItTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SnackRepositoryTest extends AbstractItTest {

    @Autowired
    private SnackRepository snackRepository;

    @Test
    void findById() {
        Snack savedSnack = snackRepository.save(Snack.CHOCOLATE);

        Optional<Snack> result = snackRepository.findById(savedSnack.getId());

        assertThat(result).isPresent();

        result.ifPresent(snack -> {
            assertThat(snack).isEqualTo(Snack.CHOCOLATE);
        });
    }
}
