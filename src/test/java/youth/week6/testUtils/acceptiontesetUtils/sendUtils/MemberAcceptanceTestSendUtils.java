package youth.week6.testUtils.acceptiontesetUtils.sendUtils;

import static youth.week6.testUtils.SnakeCaseObjectMapper.요청전문_snake_case_변경;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import youth.week6.member.dto.request.LoginRequestDto;
import youth.week6.member.dto.request.OrganizerMemberJoinRequestDto;
import youth.week6.member.dto.request.ParticipantJoinRequestDto;
import youth.week6.member.dto.request.ParticipantMemberJoinRequestDto;

public class MemberAcceptanceTestSendUtils {

    public static ExtractableResponse<Response> 참여자_회원가입_요청(ParticipantMemberJoinRequestDto 요청전문) {
        String 요청전문_snake_case_변경 = 요청전문_snake_case_변경(요청전문);

        return RestAssured
            .given().log().all()
            .body(요청전문_snake_case_변경)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/members/participants")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 주최자_회원가입_요청(OrganizerMemberJoinRequestDto 요청전문) {
        String 요청전문_snake_case_변경 = 요청전문_snake_case_변경(요청전문);

        return RestAssured
            .given().log().all()
            .body(요청전문_snake_case_변경)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/members/organizers")
            .then().log().all()
            .extract();
    }

    public static String 로그인을통한_JWT토큰획득(LoginRequestDto 요청전문) {
        String 요청전문_snake_case_변경 = 요청전문_snake_case_변경(요청전문);

        ExtractableResponse<Response> 로그인결과 = RestAssured
            .given().log().all()
            .body(요청전문_snake_case_변경)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/members/login")
            .then().log().all()
            .extract();

        return 로그인결과.body().asString();
    }

    public static ExtractableResponse<Response> 회원정보_조회요청(String JWT토큰) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(new Header("Authorization", "Bearer " + JWT토큰))
            .when().get("/members")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 참여자_권한_요청(String JWT토큰, ParticipantJoinRequestDto 요청전문) {
        String 요청전문_snake_case_변경 = 요청전문_snake_case_변경(요청전문);

        return RestAssured
            .given().log().all()
            .body(요청전문_snake_case_변경)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(new Header("Authorization", "Bearer " + JWT토큰))
            .when().patch("/members/participants")
            .then().log().all()
            .extract();
    }
}
