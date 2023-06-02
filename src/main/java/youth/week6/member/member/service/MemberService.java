package youth.week6.member.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.member.entity.Members;
import youth.week6.member.member.mapper.MemberToMemberDtoMapper;
import youth.week6.member.member.repository.MembersRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembersRepository membersRepository;
    private final MemberToMemberDtoMapper dtoMapper;

    @Transactional
    public MemberDto joinParticipants(MemberJoinDto memberJoinDto, long participantId) {
        Members members = Members.from(memberJoinDto);

        members.mappingParticipantsInfo(participantId);

        membersRepository.save(members);

        return dtoMapper.to(members);
    }

    @Transactional
    public MemberDto joinOrganizers(MemberJoinDto memberJoinDto, long organizersId) {
        Members members = Members.from(memberJoinDto);

        members.mappingOrganizerInfo(organizersId);

        membersRepository.save(members);

        return dtoMapper.to(members);
    }
}
