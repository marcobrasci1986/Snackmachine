package be.avidoo.ddd.boundedcontext.snackmachine.snack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {

}
