package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import youth.week6.member.controller.dto.request.join.MemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.dto.MemberJoinDto;
import youth.week6.member.dto.OrganizerJoinDto;
import youth.week6.member.dto.ParticipantJoinDto;

@Mapper(componentModel = "spring")
public interface JoinDtoMapper {

    @Mapping(source = "localDateTimeBirthDate", target = "birthDate")
    MemberJoinDto to(MemberJoinRequestDto memberJoinRequestDto);

    OrganizerJoinDto to(OrganizerJoinRequestDto organizerJoinRequestDto);

    ParticipantJoinDto to(ParticipantJoinRequestDto participantJoinRequestDto);
}
