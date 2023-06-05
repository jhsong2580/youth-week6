package youth.week6.testUtils.acceptiontesetUtils.assertionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import youth.week6.member.controller.dto.request.join.MemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.controller.dto.response.MemberDetailResponseDto.AllergenResponseDto;

public class MemberAcceptanceTestAssertionUtils {

    public static void 참여자_회원가입_확인됨(ExtractableResponse<Response> 참여자_회원가입_요청_response,
        String 사용자_데이터베이스_아이디값) {
        assertAll(
            () -> assertThat(참여자_회원가입_요청_response.jsonPath().get("member_id").toString()).isEqualTo(
                사용자_데이터베이스_아이디값),
            () -> assertThat(참여자_회원가입_요청_response.statusCode()).isEqualTo(
                HttpStatus.CREATED.value())
        );
    }

    public static void 회원가입_중복지원_에러발생(ExtractableResponse<Response> 회원가입_요청_response) {
        assertAll(
            () -> assertThat(회원가입_요청_response.statusCode()).isEqualTo(
                HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
    }

    public static void 회원정보_확인됨(ExtractableResponse<Response> 회원정보_조회요청_response,
        MemberJoinRequestDto 사용자_정보) {
        assertAll(
            () -> assertThat(회원정보_조회요청_response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(
                회원정보_조회요청_response.jsonPath().get("member.identification").toString()).isEqualTo(
                사용자_정보.getIdentification()),
            () -> assertThat(회원정보_조회요청_response.jsonPath().get("member.sex").toString()).isEqualTo(
                사용자_정보.getSex().name()),
            () -> assertThat(회원정보_조회요청_response.jsonPath().get("member.name").toString()).isEqualTo(
                사용자_정보.getName()),
            () -> assertThat(
                회원정보_조회요청_response.jsonPath().get("member.email").toString()).isEqualTo(
                사용자_정보.getEmail())
        );
    }

    public static void 주최자정보_확인됨(ExtractableResponse<Response> 회원정보_조회요청_response,
        OrganizerJoinRequestDto 주최자_정보) {
        assertAll(
            () -> assertThat(회원정보_조회요청_response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(
                회원정보_조회요청_response.jsonPath().get("organizer.belong").toString()).isEqualTo(
                주최자_정보.getBelong())
        );
    }

    public static void 참여자정보_확인됨(ExtractableResponse<Response> 회원정보_조회요청_response,
        ParticipantJoinRequestDto 참여자_정보) {
        assertAll(
            () -> assertThat(회원정보_조회요청_response.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(
                회원정보_조회요청_response.jsonPath().get("participant.description").toString()).isEqualTo(
                참여자_정보.getDescription()),
            () -> assertThat(회원정보_조회요청_response.jsonPath()
                .getList("participant.allergens", AllergenResponseDto.class))
                .extracting("id")
                .containsExactlyInAnyOrderElementsOf(참여자_정보.getAllergens())
        );
    }

    public static void 참여자권한_추가됨(ExtractableResponse<Response> 참여자_권한_요청_response) {
        assertThat(참여자_권한_요청_response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 참여자권한_추가불가(ExtractableResponse<Response> 참여자_권한_요청_response) {
        assertThat(참여자_권한_요청_response.statusCode()).isEqualTo(
            HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static void 주최자권한_추가됨(ExtractableResponse<Response> 참여자_권한_요청_response) {
        assertThat(참여자_권한_요청_response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 주최자권한_추가불가(ExtractableResponse<Response> 참여자_권한_요청_response) {
        assertThat(참여자_권한_요청_response.statusCode()).isEqualTo(
            HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static void 사용자정보_변경됨(ExtractableResponse<Response> 사용자_정보변경_요청_response) {
        assertThat(사용자_정보변경_요청_response.statusCode()).isEqualTo(
            HttpStatus.OK.value());
    }
}
