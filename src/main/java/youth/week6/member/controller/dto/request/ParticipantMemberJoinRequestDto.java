package youth.week6.member.controller.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
public class ParticipantMemberJoinRequestDto {

    @Valid
    @NotNull(message = "member info required")
    private MemberJoinRequestDto member;
    @Valid
    @NotNull(message = "participant info required")
    private ParticipantJoinRequestDto participant;
}
