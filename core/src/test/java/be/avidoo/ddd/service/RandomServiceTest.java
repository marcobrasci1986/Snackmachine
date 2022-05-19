//package be.avidoo.ddd.service;
//
//import be.avidoo.ddd.RandomService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ServiceTestConfiguration.class)
//@TestPropertySource("classpath:application-test.properties")
//class RandomServiceTest {
//
//    @Autowired
//    private RandomService randomService;
//
//    /**
//     * How to make this work?
//     */
//    @Test
//    void name() {
//        assertThat(randomService.findName()).isEqualTo("DDD Marco");
//    }
//}
