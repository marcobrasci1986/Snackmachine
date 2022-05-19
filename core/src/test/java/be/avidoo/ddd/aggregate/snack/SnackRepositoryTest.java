package be.avidoo.ddd.aggregate.snack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SnackTestConfiguration.class)
//@TestPropertySource(properties = "logging.level.root=WARN")
class SnackRepositoryTest {

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
