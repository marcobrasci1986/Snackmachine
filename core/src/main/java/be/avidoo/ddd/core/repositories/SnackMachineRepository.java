package be.avidoo.ddd.core.repositories;

import be.avidoo.ddd.core.aggregate.SnackMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackMachineRepository extends JpaRepository<SnackMachine, Long> {

}
