package youth.week6.member.mapper;

import org.mapstruct.Mapper;
import youth.week6.member.dto.request.MemberJoinRequestDto;
import youth.week6.member.dto.request.OrganizerJoinRequestDto;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;

@Mapper(componentModel = "spring")
public interface OrganizerJoinRequestDtoToJoinDtoMapper {

    OrganizerJoinDto to(OrganizerJoinRequestDto organizerJoinRequestDto);
}
