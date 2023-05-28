package youth.week6.member.participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.member.participant.entity.Participants;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {

}
