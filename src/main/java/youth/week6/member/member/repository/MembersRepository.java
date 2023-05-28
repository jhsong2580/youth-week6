package youth.week6.member.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.member.member.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

}
