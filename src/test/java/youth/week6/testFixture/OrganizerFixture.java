package youth.week6.testFixture;

import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto.OrganizerUpdateRequestDto;

public enum OrganizerFixture {
    주최자정보_정상입력("COMPANY"),
    주최자정보_정상입력_1("COMPANY1");

    private final String 소속;

    OrganizerFixture(String 소속) {
        this.소속 = 소속;
    }

    public OrganizerJoinRequestDto 회원가입_주최자_요청전문() {
        return new OrganizerJoinRequestDto(소속);
    }
    public OrganizerUpdateRequestDto 주최자_업데이트_요청전문() {
        return new OrganizerUpdateRequestDto(소속);
    }

    public String 소속() {
        return 소속;
    }
}
