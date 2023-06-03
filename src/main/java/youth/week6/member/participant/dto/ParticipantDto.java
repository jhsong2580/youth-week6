package youth.week6.member.participant.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ParticipantDto {
    private final Long id;
    private final String description;
    private final List<AllergenDto> allergens;


    @Builder
    public ParticipantDto(Long id, String description, List<AllergenDto> allergens) {
        this.id = id;
        this.description = description;
        this.allergens = allergens;
    }
}
