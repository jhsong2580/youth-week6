package youth.week6.testUtils.acceptiontesetUtils.assertionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

public class MemberAcceptanceTestAssertionUtils {

    public static void 참여자_회원가입_확인됨(ExtractableResponse<Response> 참여자_회원가입_요청_response, String 사용자_데이터베이스_아이디값) {
        assertAll(
            () -> assertThat(참여자_회원가입_요청_response.jsonPath().get("member_id").toString()).isEqualTo(사용자_데이터베이스_아이디값),
            () -> assertThat(참여자_회원가입_요청_response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        );
    }

    public static void 회원가입_중복지원_에러발생(ExtractableResponse<Response> 회원가입_요청_response) {
        assertAll(
            () -> assertThat(회원가입_요청_response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
    }
}
