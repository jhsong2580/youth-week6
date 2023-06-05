package youth.week6.member.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class ParticipantResponseDto {
    private final Long id;
    private final String description;
    private final List<AllergenResponseDto> allergens;

    public ParticipantResponseDto(Long id, String description, List<AllergenResponseDto> allergens) {
        this.id = id;
        this.description = description;
        this.allergens = allergens;
    }
}
