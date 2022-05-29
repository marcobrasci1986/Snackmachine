package be.avidoo.ddd.boundedcontext.department;

import be.avidoo.ddd.boundedcontext.AbstractDataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentRepositoryTest extends AbstractDataJpaTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void findAll() {
        List<Department> departments = departmentRepository.findAll();

        assertThat(departments.size()).isEqualTo(3);
    }
}
