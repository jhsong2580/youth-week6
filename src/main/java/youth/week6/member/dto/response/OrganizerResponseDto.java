package youth.week6.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrganizerResponseDto {
    private final Long id;
    private final String belong;

    @Builder
    public OrganizerResponseDto(Long id, String belong) {
        this.id = id;
        this.belong = belong;
    }
}
