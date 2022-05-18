package be.avidoo.ddd.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

/**
 * https://localcoder.org/how-to-test-maven-module-project-with-spring-boot
 */
@TestConfiguration
@ComponentScan(basePackages = {"be.avidoo.ddd.service"})
@EnableAutoConfiguration
public class ServiceTestConfiguration {
}
