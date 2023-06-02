package youth.week6.member.mapper;

import org.mapstruct.Mapper;
import youth.week6.member.dto.request.OrganizerJoinRequestDto;
import youth.week6.member.dto.request.ParticipantJoinRequestDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;

@Mapper(componentModel = "spring")
public interface ParticipantJoinRequestDtoToJoinDtoMapper {

    ParticipantJoinDto to(ParticipantJoinRequestDto participantJoinRequestDto);
}
