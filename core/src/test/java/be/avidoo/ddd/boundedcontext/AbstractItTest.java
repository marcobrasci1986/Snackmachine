package be.avidoo.ddd.boundedcontext;

import be.avidoo.ddd.TestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = TestApplication.class)
@Testcontainers
@ActiveProfiles("test")
public class AbstractItTest {
}
