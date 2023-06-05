package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import youth.week6.member.dto.request.OrganizerJoinRequestDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;

@Mapper(componentModel = "spring")
public interface OrganizerJoinRequestDtoToJoinDtoMapper {

    OrganizerJoinDto to(OrganizerJoinRequestDto organizerJoinRequestDto);
}