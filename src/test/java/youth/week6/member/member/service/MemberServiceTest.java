package youth.week6.member.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.dto.MemberJoinDto;
import youth.week6.member.member.entity.Sex;
import youth.week6.member.member.mapper.MemberToMemberDtoMapper;
import youth.week6.member.member.repository.MembersRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MembersRepository membersRepository;
    private MemberService memberService;

    private MemberToMemberDtoMapper mapper = Mappers.getMapper(
        MemberToMemberDtoMapper.class);

    @BeforeEach
    public void init() {
        memberService = new MemberService(membersRepository, mapper);
    }

    @Test
    @DisplayName("회원 가입 - 회원 도메인 - 참여자")
    public void test1() {
        //given
        when(membersRepository.save(any())).thenReturn(null);
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
            .birthDate(LocalDateTime.now())
            .name("name")
            .email("email@email.com")
            .identification("id")
            .password("password")
            .sex(Sex.MAIL)
            .build();

        //when
        MemberDto memberDto = memberService.joinParticipants(memberJoinDto, 1L);

        //then
        assertAll(
            () -> assertThat(memberDto.getIdentification()).isEqualTo(
                memberJoinDto.getIdentification()),
            () -> assertThat(memberDto.getEmail()).isEqualTo(memberJoinDto.getEmail()),
            () -> assertThat(memberDto.getBirthDate()).isEqualTo(memberJoinDto.getBirthDate()),
            () -> assertThat(memberDto.getParticipantsId()).isEqualTo(1L)
        );
    }

    @Test
    @DisplayName("회원 가입 - 회원도메인 - 주최자")
    public void test2() {
        //given
        when(membersRepository.save(any())).thenReturn(null);
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
            .birthDate(LocalDateTime.now())
            .name("name")
            .email("email@email.com")
            .identification("id")
            .password("password")
            .sex(Sex.MAIL)
            .build();

        //when
        MemberDto memberDto = memberService.joinOrganizers(memberJoinDto, 1L);

        //then
        assertAll(
            () -> assertThat(memberDto.getIdentification()).isEqualTo(
                memberJoinDto.getIdentification()),
            () -> assertThat(memberDto.getEmail()).isEqualTo(memberJoinDto.getEmail()),
            () -> assertThat(memberDto.getBirthDate()).isEqualTo(memberJoinDto.getBirthDate()),
            () -> assertThat(memberDto.getOrganizerId()).isEqualTo(1L)
        );
    }
}