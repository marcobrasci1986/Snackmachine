package be.avidoo.ddd.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HelloService.class, NameService.class})
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void print() {
        assertThat(helloService.printHello()).isEqualTo("Hello Marco");
    }
}
