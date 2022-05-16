package be.avidoo.ddd.core.repositories;

import be.avidoo.ddd.core.aggregate.SnackMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackMachineRepository extends JpaRepository<SnackMachine, Long> {
}
