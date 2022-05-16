package be.avidoo.ddd.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * https://localcoder.org/how-to-test-maven-module-project-with-spring-boot
 */
@TestConfiguration
@EnableJpaRepositories(basePackages = {"be.avidoo.ddd.core.aggregate"})
@EntityScan(basePackages = {"be.avidoo.ddd.core.aggregate"})
@EnableAutoConfiguration
public class CoreTestConfiguration {
}
