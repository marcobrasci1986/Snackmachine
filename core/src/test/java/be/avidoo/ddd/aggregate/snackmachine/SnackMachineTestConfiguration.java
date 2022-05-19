package be.avidoo.ddd.aggregate.snackmachine;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

/**
 * https://localcoder.org/how-to-test-maven-module-project-with-spring-boot
 */
@TestConfiguration
@EnableJpaRepositories(basePackages = {"be.avidoo.ddd.aggregate"})
@EntityScan(basePackages = {"be.avidoo.ddd.aggregate"})
@Import({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class SnackMachineTestConfiguration {
}
