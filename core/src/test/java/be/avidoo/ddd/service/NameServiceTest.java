package be.avidoo.ddd.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class NameServiceTest {

    @Autowired
    private NameService nameService;

    @Test
    void name() {
        assertThat(nameService.findName()).isEqualTo("DDD Marco");
    }

    @TestConfiguration
    @ComponentScan(basePackages = {"be.avidoo.ddd.service"})
    static class MyTestConfig {
    }
}
