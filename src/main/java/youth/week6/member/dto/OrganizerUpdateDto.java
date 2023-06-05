package youth.week6.member.dto;

import lombok.Getter;

@Getter
public class OrganizerUpdateDto {

    private final String belong;

    public OrganizerUpdateDto(String belong) {
        this.belong = belong;
    }
}
