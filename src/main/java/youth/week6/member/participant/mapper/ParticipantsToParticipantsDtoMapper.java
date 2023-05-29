package youth.week6.member.participant.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import youth.week6.member.participant.dto.AllergenDto;
import youth.week6.member.participant.dto.ParticipantDto;
import youth.week6.member.participant.entity.Participants;

@Mapper(componentModel = "spring")
public interface ParticipantsToParticipantsDtoMapper {


    @Mapping(expression = "java(getAllergens(participants))", target = "allergens")
    ParticipantDto to(Participants participants);

    default List<AllergenDto> getAllergens(Participants participants) {
        return participants.getAllergens().stream()
            .map(allergens -> new AllergenDto(allergens.getId(), allergens.getName()))
            .collect(Collectors.toUnmodifiableList());
    }
}
