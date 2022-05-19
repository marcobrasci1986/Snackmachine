package be.avidoo.ddd.aggregate.snack;

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
@EnableJpaRepositories(basePackages = {"be.avidoo.ddd.aggregate.snack"})
@EntityScan(basePackages = {"be.avidoo.ddd.aggregate.snack"})
@Import({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@TestPropertySource(properties = {"logging.level.root=WARN"})
class SnackTestConfiguration {
}
