package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.dto.OrganizerJoinDto;

@Mapper(componentModel = "spring")
public interface OrganizerJoinRequestDtoToJoinDtoMapper {

    OrganizerJoinDto to(OrganizerJoinRequestDto organizerJoinRequestDto);
}
