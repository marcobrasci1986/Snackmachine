package be.avidoo.ddd.boundedcontext.department;

import be.avidoo.ddd.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Employee extends AbstractEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Builder
    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}
