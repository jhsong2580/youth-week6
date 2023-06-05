package youth.week6.member.controller.dto.request.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.member.entity.Sex;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
public class MemberDetailUpdateRequestDto {

    @NotNull
    @Valid
    private MemberUpdateRequestDto member;
    @Valid
    private OrganizerUpdateRequestDto organizer;
    @Valid
    private ParticipantUpdateRequestDto participant;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
    public static class MemberUpdateRequestDto {

        @NotEmpty(message = "member.name required")
        private String name; // 이름
        @NotNull(message = "member.birth_date required")
        @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "member.birth_date pattern is not matched ex)1991-07-07")
        private String birthDate; // 생년월일
        @Pattern(regexp = "(\\W|^)(MAIL|FEMALE)(\\W|$)", message = "member.sex require MAIL or FEMALE")
        private String sex; // 성별
        @NotEmpty(message = "member.identification required")
        private String identification; // Login ID
        @Pattern(regexp = "^(?=.[a-zA-Z])((?=.\\d)|(?=.*\\W)).{6,20}$",
            message = "member.password must contain between 6 and 20 English uppercase and lowercase letters, special characters, and numbers. ex)Mypassword1!"
        )
        private String password; // Login Password
        @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+", message = "member.email pattern not match")
        private String email;

        public Sex getSex() {
            return Sex.valueOf(this.sex);
        }

        public String getBirthDate() {
            return birthDate;
        }

        @JsonIgnore
        public LocalDateTime getLocalDateTimeBirthDate() {
            List<Integer> birthDate = Arrays.stream(this.birthDate.split("-"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

            return LocalDateTime.of(birthDate.get(0), birthDate.get(1), birthDate.get(2), 0, 0);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
    public static class ParticipantUpdateRequestDto {

        @NotEmpty(message = "participant.description is required")
        private String description; //자기소개
        @NotNull(message = "participant.allergens is required")
        private List<Long> allergens;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
    public static class OrganizerUpdateRequestDto {

        @NotEmpty(message = "organizer.belong is required")
        private String belong; // 소속

    }
}
