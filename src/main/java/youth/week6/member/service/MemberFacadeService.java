package youth.week6.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.member.service.MemberService;
import youth.week6.member.organizer.dto.OrganizerDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;
import youth.week6.member.organizer.service.OrganizerService;
import youth.week6.member.participant.dto.ParticipantDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;
import youth.week6.member.participant.service.ParticipantService;
import youth.week6.member.service.dto.MemberDetailDto;

@Service
@RequiredArgsConstructor
public class MemberFacadeService {

    private final MemberService memberService;
    private final ParticipantService participantService;
    private final OrganizerService organizerService;

    @Transactional
    public long join(MemberJoinDto memberJoinDto, ParticipantJoinDto participantJoinDto) {
        ParticipantDto participantDto = participantService.join(participantJoinDto);
        MemberDto memberDto = memberService.joinParticipants(memberJoinDto, participantDto.getId());

        return memberDto.getId();
    }

    @Transactional
    public long join(MemberJoinDto memberJoinDto, OrganizerJoinDto organizerJoinDto) {
        OrganizerDto organizerDto = organizerService.join(organizerJoinDto);
        MemberDto memberDto = memberService.joinOrganizers(memberJoinDto, organizerDto.getId());

        return memberDto.getId();
    }

    @Transactional(readOnly = true)
    public MemberDetailDto get(long memberId) {
        MemberDto memberDto = memberService.get(memberId);
        ParticipantDto participantDto =
            memberDto.getParticipantsId() != null ? participantService.get(
                memberDto.getParticipantsId()) : null;
        OrganizerDto organizerDto = memberDto.getOrganizerId() != null ? organizerService.get(
            memberDto.getOrganizerId()) : null;

        return new MemberDetailDto(memberDto, participantDto, organizerDto);
    }

    @Transactional
    public void joinParticipant(Long memberId, ParticipantJoinDto participantJoinDto) {
        ParticipantDto participantDto = participantService.join(participantJoinDto);
        memberService.mapParticipantInfo(memberId, participantDto.getId());
    }

    @Transactional
    public void joinOrganizer(Long memberId, OrganizerJoinDto organizerJoinDto) {
        OrganizerDto organizerDto = organizerService.join(organizerJoinDto);
        memberService.mapOrganizerInfo(memberId, organizerDto.getId());
    }
}
