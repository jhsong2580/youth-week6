package youth.week6.member.organizer.dto;

import lombok.Getter;

@Getter
public class OrganizerDto {

    private final Long id;
    private final String belong;

    public OrganizerDto(Long id, String belong) {
        this.id = id;
        this.belong = belong;
    }
}
