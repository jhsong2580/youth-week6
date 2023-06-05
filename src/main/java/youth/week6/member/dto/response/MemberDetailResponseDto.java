package youth.week6.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDetailResponseDto {

    private final MemberResponseDto member;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ParticipantResponseDto participant;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final OrganizerResponseDto organizer;

    @Builder
    public MemberDetailResponseDto(MemberResponseDto member, ParticipantResponseDto participant,
        OrganizerResponseDto organizer) {
        this.member = member;
        this.participant = participant;
        this.organizer = organizer;
    }
}
