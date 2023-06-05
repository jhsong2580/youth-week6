package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import youth.week6.member.controller.dto.request.join.MemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto.MemberUpdateRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto.OrganizerUpdateRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto.ParticipantUpdateRequestDto;
import youth.week6.member.dto.MemberJoinDto;
import youth.week6.member.dto.MemberUpdateDto;
import youth.week6.member.dto.OrganizerJoinDto;
import youth.week6.member.dto.OrganizerUpdateDto;
import youth.week6.member.dto.ParticipantJoinDto;
import youth.week6.member.dto.ParticipantUpdateDto;

@Mapper(componentModel = "spring")
public interface UpdateDtoMapper {

    @Mapping(source = "localDateTimeBirthDate", target = "birthDate")
    MemberUpdateDto to(MemberUpdateRequestDto memberUpdateRequestDto);

    OrganizerUpdateDto to(OrganizerUpdateRequestDto organizerUpdateRequestDto);

    ParticipantUpdateDto to(ParticipantUpdateRequestDto participantUpdateRequestDto);
}
