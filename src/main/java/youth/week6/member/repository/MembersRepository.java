package youth.week6.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.member.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

}
