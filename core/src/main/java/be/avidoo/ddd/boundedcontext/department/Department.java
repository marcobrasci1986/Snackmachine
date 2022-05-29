package be.avidoo.ddd.boundedcontext.department;

import be.avidoo.ddd.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Department extends AbstractEntity {

    @Column(name = "description")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private List<Employee> employees;

    @Builder
    public Department(String description, List<Employee> employees) {
        this.description = description;
        this.employees = employees;
    }
}
