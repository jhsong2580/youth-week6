package youth.week6.member.dto;

import lombok.Getter;

@Getter
public class OrganizerJoinDto {

    private final String belong;

    public OrganizerJoinDto(String belong) {
        this.belong = belong;
    }
}
