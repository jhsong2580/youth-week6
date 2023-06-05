package youth.week6.member.dto.response;

import lombok.Getter;

@Getter
public class AllergenResponseDto {
    private final Long id;
    private final String name;

    public AllergenResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
