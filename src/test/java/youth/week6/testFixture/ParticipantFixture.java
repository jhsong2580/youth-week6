package youth.week6.testFixture;

import java.util.Arrays;
import java.util.stream.Collectors;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto.ParticipantUpdateRequestDto;
import youth.week6.member.dto.ParticipantUpdateDto;

public enum ParticipantFixture {
    참여자정보_정상입력("자기소개"),
    참여자정보_정상입력1("자기소개1");

    private final String 자기소개;

    ParticipantFixture(String 자기소개) {
        this.자기소개 = 자기소개;
    }

    public ParticipantJoinRequestDto 회원가입_참여자_요청전문(Long... 취식_제한_재료_ID) {
        return new ParticipantJoinRequestDto(
            자기소개, Arrays.stream(취식_제한_재료_ID)
            .collect(Collectors.toList())
        );
    }

    public ParticipantUpdateRequestDto 참여자_업데이트_요청전문(Long... 취식_제한_재료_ID) {
        return new ParticipantUpdateRequestDto(
            자기소개, Arrays.stream(취식_제한_재료_ID)
            .collect(Collectors.toList())
        );
    }

    public String 자기소개() {
        return 자기소개;
    }
}
