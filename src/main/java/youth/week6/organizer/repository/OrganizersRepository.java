package youth.week6.organizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.organizer.entity.Organizers;

public interface OrganizersRepository extends JpaRepository<Organizers, Long> {

}
