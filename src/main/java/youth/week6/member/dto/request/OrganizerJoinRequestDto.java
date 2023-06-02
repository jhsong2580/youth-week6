package youth.week6.member.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizerJoinRequestDto {

    @NotEmpty(message = "organizer.belong is required")
    private String belong; // 소속

}
