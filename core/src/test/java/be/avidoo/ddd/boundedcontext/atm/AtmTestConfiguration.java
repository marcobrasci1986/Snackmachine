package be.avidoo.ddd.boundedcontext.atm;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * https://localcoder.org/how-to-test-maven-module-project-with-spring-boot
 */
@TestConfiguration
@EnableJpaRepositories(basePackages = {"be.avidoo.ddd.boundedcontext.atm"})
@EntityScan(basePackages = {"be.avidoo.ddd.boundedcontext.atm"})
class AtmTestConfiguration {
}
