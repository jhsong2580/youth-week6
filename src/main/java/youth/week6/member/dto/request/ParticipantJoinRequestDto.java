package youth.week6.member.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantJoinRequestDto {
    private String description; //자기소개
    private List<Long> allergens;
}
