package youth.week6.testFixture;

import youth.week6.member.controller.dto.request.join.MemberJoinRequestDto;

public enum MemberFixture {
    사용자정보_정상입력("name", "1991-07-30", "MAIL", "identification", "New1234!!", "thdwmdgns@naver.com", "자기소개");


    private final String 참여자이름;
    private final String 생년월일;
    private final String 성별;
    private final String 아이디;
    private final String 패스워드;
    private final String 이메일;
    private final String 자기소개;

    MemberFixture(String 참여자이름, String 생년월일, String 성별, String 아이디, String 패스워드, String 이메일,
        String 자기소개) {
        this.참여자이름 = 참여자이름;
        this.생년월일 = 생년월일;
        this.성별 = 성별;
        this.아이디 = 아이디;
        this.패스워드 = 패스워드;
        this.이메일 = 이메일;
        this.자기소개 = 자기소개;
    }

    public MemberJoinRequestDto 회원가입_사용자_요청전문() {
        return new MemberJoinRequestDto(
            참여자이름, 생년월일, 성별, 아이디, 패스워드, 이메일
        );
    }

    public String 참여자이름() {
        return 참여자이름;
    }

    public String 생년월일() {
        return 생년월일;
    }

    public String 성별() {
        return 성별;
    }

    public String 아이디() {
        return 아이디;
    }

    public String 패스워드() {
        return 패스워드;
    }

    public String 이메일() {
        return 이메일;
    }

    public String 자기소개() {
        return 자기소개;
    }
}
