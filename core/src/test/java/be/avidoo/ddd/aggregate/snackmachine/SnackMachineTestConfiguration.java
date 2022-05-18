package be.avidoo.ddd.aggregate.snackmachine;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

/**
 * https://localcoder.org/how-to-test-maven-module-project-with-spring-boot
 */
@TestConfiguration
@EnableJpaRepositories(basePackages = {"be.avidoo.ddd.aggregate"})
@EntityScan(basePackages = {"be.avidoo.ddd.aggregate"})
@EnableAutoConfiguration
@TestPropertySource(properties = {"logging.level.root=WARN"})
public class SnackMachineTestConfiguration {
}
