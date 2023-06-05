package youth.week6.testFixture;

import java.util.Arrays;
import java.util.stream.Collectors;
import youth.week6.member.controller.dto.request.ParticipantJoinRequestDto;

public enum ParticipantFixture {
    참여자정보_정상입력("자기소개");

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

    public String 자기소개() {
        return 자기소개;
    }
}
