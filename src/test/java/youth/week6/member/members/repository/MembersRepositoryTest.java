package youth.week6.member.members.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import youth.week6.member.dto.MemberJoinDto;
import youth.week6.member.member.entity.Members;
import youth.week6.member.member.entity.Sex;
import youth.week6.member.member.repository.MembersRepository;
import youth.week6.testUtils.SpringBootTestHelper;


class MembersRepositoryTest extends SpringBootTestHelper {

    @Autowired
    private MembersRepository membersRepository;
    private Members members;

    @BeforeEach
    public void init() {
        super.init();
        MemberJoinDto memberJoinDto = new MemberJoinDto("name", LocalDateTime.now(), Sex.FEMALE,
            "id", "password", "email");

        members = Members.from(memberJoinDto);

        membersRepository.save(members);
    }

    @Test
    @DisplayName("Member 정보 조회시 등록한 정보들을 확인할 수 있다. ")
    public void test1() {
        //given
        Members savedMember = membersRepository.findById(members.getId()).get();

        //then
        assertAll(
            () -> assertThat(savedMember.getMemberInfo()).isEqualTo(members.getMemberInfo()),
            () -> assertThat(savedMember.getAuthenticationInfo()).isEqualTo(members.getAuthenticationInfo())
        );
    }

    @Test
    @DisplayName("Member 정보 조회시 등록한 정보들을 확인할 수 있다. ")
    public void test2() {
        //given
        Members savedMember = membersRepository.findById(members.getId()).get();

        //then
        assertAll(
            () -> assertThat(savedMember.getMemberInfo()).isEqualTo(members.getMemberInfo()),
            () -> assertThat(savedMember.getAuthenticationInfo()).isEqualTo(members.getAuthenticationInfo())
        );
    }
}