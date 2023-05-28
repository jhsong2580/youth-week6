package youth.week6.participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.participant.entity.Allergens;

public interface AllergensRepository extends JpaRepository<Allergens, Long> {

}
