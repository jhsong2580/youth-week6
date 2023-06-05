package youth.week6.member.service.dto;

import lombok.Getter;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.organizer.dto.OrganizerDto;
import youth.week6.member.participant.dto.ParticipantDto;

@Getter
public class MemberDetailDto {
    private final MemberDto memberDto;
    private final ParticipantDto participantDto;
    private final OrganizerDto organizerDto;

    public MemberDetailDto(MemberDto memberDto, ParticipantDto participantDto,
        OrganizerDto organizerDto) {
        this.memberDto = memberDto;
        this.participantDto = participantDto;
        this.organizerDto = organizerDto;
    }
}
