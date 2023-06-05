package youth.week6.member.controller.dto.request.join;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
public class OrganizerMemberJoinRequestDto {
    @Valid
    @NotNull(message = "member info required")
    private MemberJoinRequestDto member;
    @Valid
    @NotNull(message = "organizer info required")
    private OrganizerJoinRequestDto organizer;
}
