package youth.week6.testUtils.acceptiontesetUtils.sendUtils;

import static youth.week6.testUtils.SnakeCaseObjectMapper.요청전문_snake_case_변경;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import youth.week6.member.dto.request.OrganizerMemberJoinRequestDto;
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
}
