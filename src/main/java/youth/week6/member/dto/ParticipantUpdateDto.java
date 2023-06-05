package youth.week6.member.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ParticipantUpdateDto {
    private List<Long> allergens;
    private String description;
}
