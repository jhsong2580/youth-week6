package youth.week6.member.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static youth.week6.testFixture.AllergensFixture.BEEF;
import static youth.week6.testFixture.AllergensFixture.MILK;
import static youth.week6.testFixture.MemberFixture.사용자정보_정상입력;
import static youth.week6.testFixture.MemberFixture.사용자정보_정상입력_1;
import static youth.week6.testFixture.OrganizerFixture.주최자정보_정상입력;
import static youth.week6.testFixture.OrganizerFixture.주최자정보_정상입력_1;
import static youth.week6.testFixture.ParticipantFixture.참여자정보_정상입력;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.사용자정보_변경됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.주최자권한_추가됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.주최자권한_추가불가;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.주최자정보_확인됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.참여자_회원가입_확인됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.참여자권한_추가됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.참여자권한_추가불가;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.참여자정보_확인됨;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.회원가입_중복지원_에러발생;
import static youth.week6.testUtils.acceptiontesetUtils.assertionUtils.MemberAcceptanceTestAssertionUtils.회원정보_확인됨;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.로그인을통한_JWT토큰획득;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.사용자_정보변경_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.주최자_권한_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.주최자_회원가입_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.참여자_권한_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.참여자_회원가입_요청;
import static youth.week6.testUtils.acceptiontesetUtils.sendUtils.MemberAcceptanceTestSendUtils.회원정보_조회요청;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import youth.week6.member.controller.dto.request.join.MemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerMemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantMemberJoinRequestDto;
import youth.week6.member.controller.dto.request.login.LoginRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.testFixture.AllergensFixture;
import youth.week6.testUtils.SpringBootTestHelper;

class MemberControllerTest extends SpringBootTestHelper {

    private Map<AllergensFixture, Allergens> allergens;
    private MemberJoinRequestDto 사용자_정보 = 사용자정보_정상입력.회원가입_사용자_요청전문();

    private ParticipantJoinRequestDto 참여자_정보;
    private OrganizerJoinRequestDto 주최자_정보 = 주최자정보_정상입력.회원가입_주최자_요청전문();

    @BeforeEach
    public void init() {
        super.init();
        super.savedAllergens();
        allergens = super.findAllAllergens();

        참여자_정보 = 참여자정보_정상입력.회원가입_참여자_요청전문(
            allergens.get(BEEF).getId(),
            allergens.get(MILK).getId()
        );
    }

    @Test
    public void 참여자_회원가입() {

        String 예상_사용자_데이터베이스_아이디값 = "1";
        //given
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
        OrganizerMemberJoinRequestDto 요청전문 = new OrganizerMemberJoinRequestDto(
            사용자_정보, 주최자_정보);

        //when
        ExtractableResponse<Response> 주최자_회원가입_요청_response = 주최자_회원가입_요청(요청전문);

        //then
        assertAll(
            () -> 참여자_회원가입_확인됨(주최자_회원가입_요청_response, 예상_사용자_데이터베이스_아이디값)
        );

    }

    @Test
    public void 참여자_정보조회() {
        //given
        String JWT_토큰 = 참여자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 회원정보_조회요청_response = 회원정보_조회요청(JWT_토큰);

        //then
        assertAll(
            () -> 회원정보_확인됨(회원정보_조회요청_response, 사용자_정보),
            () -> 참여자정보_확인됨(회원정보_조회요청_response, 참여자_정보)
        );
    }


    @Test
    public void 주최자_정보조회() {
        //given
        String JWT_토큰 = 주최자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 회원정보_조회요청_response = 회원정보_조회요청(JWT_토큰);

        //then
        assertAll(
            () -> 회원정보_확인됨(회원정보_조회요청_response, 사용자_정보),
            () -> 주최자정보_확인됨(회원정보_조회요청_response, 주최자_정보)
        );
    }

    @Test
    public void 중복_회원가입() {
        //given
        MemberJoinRequestDto 사용자_정보 = 사용자정보_정상입력.회원가입_사용자_요청전문();
        OrganizerJoinRequestDto 주최자_정보 = 주최자정보_정상입력.회원가입_주최자_요청전문();
        OrganizerMemberJoinRequestDto 요청전문 = new OrganizerMemberJoinRequestDto(
            사용자_정보, 주최자_정보);
        주최자_회원가입_요청(요청전문);

        //when
        ExtractableResponse<Response> 주최자_회원가입_요청_response = 주최자_회원가입_요청(요청전문);

        //then
        회원가입_중복지원_에러발생(주최자_회원가입_요청_response);
    }

    @Test
    public void 참여자권한_신청() {
        //given
        String JWT토큰 = 주최자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 참여자_권한_요청_response = 참여자_권한_요청(JWT토큰, 참여자_정보);

        //then
        참여자권한_추가됨(참여자_권한_요청_response);
    }

    @Test
    public void 참여자권한_중복신청불가() {
        //given
        String JWT토큰 = 참여자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 참여자_권한_요청_response = 참여자_권한_요청(JWT토큰, 참여자_정보);

        //then
        참여자권한_추가불가(참여자_권한_요청_response);
    }

    @Test
    public void 주최자권한_신청() {
        //given
        String JWT토큰 = 참여자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 주최자_권한_요청_response = 주최자_권한_요청(JWT토큰, 주최자_정보);

        //then
        주최자권한_추가됨(주최자_권한_요청_response);
    }

    @Test
    public void 주최자권한_중복신청불가() {
        //given
        String JWT토큰 = 주최자_회원가입_JWT_토큰받기();

        //when
        ExtractableResponse<Response> 주최자_권한_요청_response = 주최자_권한_요청(JWT토큰, 주최자_정보);

        //then
        주최자권한_추가불가(주최자_권한_요청_response);
    }

    @Test
    public void 주최자_정보_업데이트() {
        //given
        String JWT_토큰 = 주최자_회원가입_JWT_토큰받기();
        MemberDetailUpdateRequestDto 요청전문 = new MemberDetailUpdateRequestDto(
            사용자정보_정상입력_1.사용자_업데이트_요청전문(),
            주최자정보_정상입력_1.주최자_업데이트_요청전문(), null);

        //when
        ExtractableResponse<Response> 사용자_정보변경_요청_response = 사용자_정보변경_요청(JWT_토큰, 요청전문);

        //then
        사용자정보_변경됨(사용자_정보변경_요청_response);
    }

    private String 주최자_회원가입_JWT_토큰받기() {
        OrganizerMemberJoinRequestDto 요청전문 = new OrganizerMemberJoinRequestDto(
            사용자_정보, 주최자_정보);
        주최자_회원가입_요청(요청전문);
        return 로그인을통한_JWT토큰획득(
            new LoginRequestDto(사용자_정보.getIdentification(), 사용자_정보.getPassword()));
    }

    private String 참여자_회원가입_JWT_토큰받기() {
        ParticipantMemberJoinRequestDto 요청전문 = new ParticipantMemberJoinRequestDto(
            사용자_정보, 참여자_정보);
        참여자_회원가입_요청(요청전문);
        String JWT_토큰 = 로그인을통한_JWT토큰획득(
            new LoginRequestDto(사용자_정보.getIdentification(), 사용자_정보.getPassword()));
        return JWT_토큰;
    }
}