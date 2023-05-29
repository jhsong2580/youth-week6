package youth.week6.member.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.member.entity.Members;
import youth.week6.member.member.entity.embeded.AuthenticationInfo;
import youth.week6.member.member.entity.embeded.MemberInfo;
import youth.week6.member.member.mapper.MemberToMemberDtoMapper;
import youth.week6.member.member.repository.MembersRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembersRepository membersRepository;
    private final MemberToMemberDtoMapper dtoMapper;

    @Transactional
    public MemberDto joinParticipants(MemberJoinDto memberJoinDto, long participantId) {
        MemberInfo memberInfo = new MemberInfo(
            memberJoinDto.getName(),
            memberJoinDto.getBirthDate(),
            memberJoinDto.getSex(),
            memberJoinDto.getEmail()
        );

        AuthenticationInfo authenticationInfo = new AuthenticationInfo(
            memberJoinDto.getIdentification(), memberJoinDto.getPassword());

        Members members = new Members(memberInfo, authenticationInfo);
        members.mappingParticipantsInfo(participantId);

        membersRepository.save(members);

        return dtoMapper.to(members);
    }

    @Transactional
    public MemberDto joinOrganizers(MemberJoinDto memberJoinDto, long organizersId) {
        MemberInfo memberInfo = new MemberInfo(
            memberJoinDto.getName(),
            memberJoinDto.getBirthDate(),
            memberJoinDto.getSex(),
            memberJoinDto.getEmail()
        );

        AuthenticationInfo authenticationInfo = new AuthenticationInfo(
            memberJoinDto.getIdentification(), memberJoinDto.getPassword());

        Members members = new Members(memberInfo, authenticationInfo);
        members.mappingOrganizerInfo(organizersId);

        membersRepository.save(members);

        return dtoMapper.to(members);
    }
}
