package youth.week6.member.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        checkJoinMemberRequestDuplicateIdentification(memberJoinDto.getIdentification());

        Members members = saveMembersByMemberJoinDto(memberJoinDto);
        members.mappingParticipantsInfo(participantId);

        return dtoMapper.to(members);
    }



    @Transactional
    public MemberDto joinOrganizers(MemberJoinDto memberJoinDto, long organizersId) {
        checkJoinMemberRequestDuplicateIdentification(memberJoinDto.getIdentification());

        Members members = saveMembersByMemberJoinDto(memberJoinDto);
        members.mappingOrganizerInfo(organizersId);

        return dtoMapper.to(members);
    }

    private void checkJoinMemberRequestDuplicateIdentification(String identification) {
        Optional<Members> members = membersRepository.findByAuthenticationInfo_Identification(
            identification);

        if (members.isPresent()) {
            throw new IllegalArgumentException("duplicated identification join request");
        }
    }

    private Members saveMembersByMemberJoinDto(MemberJoinDto memberJoinDto) {
        Members members = Members.from(memberJoinDto);

        try {
            membersRepository.save(members);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("duplicated identification join request");
        }
        return members;
    }

    @Transactional(readOnly = true)
    public MemberDto get(long memberId) {
        Members members = membersRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "there is no member info with " + memberId)
            );

        return dtoMapper.to(members);
    }

    @Transactional
    public void mapParticipantInfo(long memberId, long participantId) {
        Members members = membersRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "there is no member info with " + memberId)
            );

        members.mappingParticipantsInfo(participantId);
    }
}
