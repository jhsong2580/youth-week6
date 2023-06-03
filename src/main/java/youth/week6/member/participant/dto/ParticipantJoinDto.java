package youth.week6.member.participant.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ParticipantJoinDto {
    private List<Long> allergens;
    private String description;
}
