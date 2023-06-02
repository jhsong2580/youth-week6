package youth.week6.member.controller;

import static youth.week6.testFixture.AllergensFixture.BEEF;
import static youth.week6.testFixture.AllergensFixture.MILK;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.참여자_회원가입_확인됨;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.주최자_회원가입_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.참여자_회원가입_요청;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import youth.week6.member.dto.request.MemberJoinRequestDto;
import youth.week6.member.dto.request.OrganizerJoinRequestDto;
import youth.week6.member.dto.request.OrganizerMemberJoinRequestDto;
import youth.week6.member.dto.request.ParticipantJoinRequestDto;
import youth.week6.member.dto.request.ParticipantMemberJoinRequestDto;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.testFixture.AllergensFixture;
import youth.week6.testFixture.MemberFixture;
import youth.week6.testFixture.OrganizerFixture;
import youth.week6.testFixture.ParticipantFixture;
import youth.week6.testUtils.SpringBootTestHelper;

class MemberControllerTest extends SpringBootTestHelper {

    Map<AllergensFixture, Allergens> allergens;

    @BeforeEach
    public void init() {
        super.init();
        super.savedAllergens();
        allergens = super.findAllAllergens();
    }

    @Test
    public void 참여자_회원가입() {

        String 예상_사용자_데이터베이스_아이디값 = "1";

        //given
        MemberJoinRequestDto 사용자_정보 = MemberFixture.사용자정보_정상입력.회원가입_사용자_요청전문();

        ParticipantJoinRequestDto 참여자_정보 = ParticipantFixture.참여자정보_정상입력.회원가입_참여자_요청전문(
            allergens.get(BEEF).getId(),
            allergens.get(MILK).getId()
        );
        ParticipantMemberJoinRequestDto 요청전문 = new ParticipantMemberJoinRequestDto(
            사용자_정보, 참여자_정보);

        //when
        ExtractableResponse<Response> 참여자_회원가입_요청_response = 참여자_회원가입_요청(요청전문);

        //then
        참여자_회원가입_확인됨(참여자_회원가입_요청_response, 예상_사용자_데이터베이스_아이디값);
    }

    @Test
    public void 주최자_회원가입() {
        String 예상_사용자_데이터베이스_아이디값 = "1";
        //given
        MemberJoinRequestDto 사용자_정보 = MemberFixture.사용자정보_정상입력.회원가입_사용자_요청전문();
        OrganizerJoinRequestDto 주최자_정보 = OrganizerFixture.주최자정보_정상입력.회원가입_주최자_요청전문();
        OrganizerMemberJoinRequestDto 요청전문 = new OrganizerMemberJoinRequestDto(
            사용자_정보, 주최자_정보);

        //when
        ExtractableResponse<Response> 주최자_회원가입_요청_response = 주최자_회원가입_요청(요청전문);

        //then
        참여자_회원가입_확인됨(주최자_회원가입_요청_response, 예상_사용자_데이터베이스_아이디값);
    }
}