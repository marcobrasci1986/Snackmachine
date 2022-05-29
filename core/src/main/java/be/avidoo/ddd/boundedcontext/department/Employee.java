package be.avidoo.ddd.boundedcontext.department;

import be.avidoo.ddd.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Employee extends AbstractEntity {

    private String name;

    @Builder
    public Employee(String name) {
        this.name = name;
    }
}
