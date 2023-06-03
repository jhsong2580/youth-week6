package youth.week6.member.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import youth.week6.member.member.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByAuthenticationInfo_Identification(String identification);
}
