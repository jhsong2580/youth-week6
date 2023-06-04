package youth.week6.member.member.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import youth.week6.member.member.dto.MemberJoinDto;

class MembersTest {

    private MemberJoinDto memberJoinDto = new MemberJoinDto(
        "memberName",
        LocalDateTime.now(),
        Sex.MAIL,
        "identification",
        "password",
        "email"
    );

    @Test
    @DisplayName("회원이 최초 생성시 USER권한만 갖는다.")
    public void test1() {
        //when
        Members members = Members.from(memberJoinDto);

        //then
        assertThat(members.getAuthenticationInfo().getRoles())
            .containsExactly(MemberRoles.USER);
    }

    @Test
    @DisplayName("회원 생성 후, 참여자 등록을 하면 참여자 권한이 부여된다.")
    public void test2() {
        //given
        Members members = Members.from(memberJoinDto);

        //when
        members.mappingParticipantsInfo(1L);

        //then
        assertThat(members.getAuthenticationInfo().getRoles())
            .containsExactlyInAnyOrderElementsOf(
                Arrays.asList(
                    MemberRoles.USER,
                    MemberRoles.PARTICIPANT
                )
            );
    }

    @Test
    @DisplayName("참여자 등록 후 참여자 권한을 제거하면 하면 참여자 권한이 제거된다.")
    public void test3() {
        //given
        Members members = Members.from(memberJoinDto);

        //when
        members.mappingParticipantsInfo(1L);
        members.unMappingParticipantsInfo();

        //then
        assertThat(members.getAuthenticationInfo().getRoles())
            .containsExactlyInAnyOrderElementsOf(
                Arrays.asList(
                    MemberRoles.USER
                )
            );
    }

    @Test
    @DisplayName("회원 생성 후, 주최자 등록을 하면 참여자 권한이 부여된다.")
    public void test4() {
        //given
        Members members = Members.from(memberJoinDto);

        //when
        members.mappingOrganizerInfo(1L);

        //then
        assertThat(members.getAuthenticationInfo().getRoles())
            .containsExactlyInAnyOrderElementsOf(
                Arrays.asList(
                    MemberRoles.USER,
                    MemberRoles.ORGANIZER
                )
            );
    }

    @Test
    @DisplayName("참여자 등록 후 주최자 권한을 제거하면 하면 주최자 권한이 제거된다.")
    public void test5() {
        //given
        Members members = Members.from(memberJoinDto);

        //when
        members.mappingOrganizerInfo(1L);
        members.unMappingOrganizerInfo();

        //then
        assertThat(members.getAuthenticationInfo().getRoles())
            .containsExactlyInAnyOrderElementsOf(
                Arrays.asList(
                    MemberRoles.USER
                )
            );
    }
}