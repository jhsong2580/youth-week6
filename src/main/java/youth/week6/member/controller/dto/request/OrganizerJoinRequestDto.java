package youth.week6.member.controller.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
public class OrganizerJoinRequestDto {

    @NotEmpty(message = "organizer.belong is required")
    private String belong; // 소속

}
