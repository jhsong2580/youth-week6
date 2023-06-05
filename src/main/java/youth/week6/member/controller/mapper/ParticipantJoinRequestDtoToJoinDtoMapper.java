package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import youth.week6.member.dto.request.ParticipantJoinRequestDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;

@Mapper(componentModel = "spring")
public interface ParticipantJoinRequestDtoToJoinDtoMapper {

    ParticipantJoinDto to(ParticipantJoinRequestDto participantJoinRequestDto);
}
