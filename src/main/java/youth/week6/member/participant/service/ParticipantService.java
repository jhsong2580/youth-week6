package youth.week6.member.participant.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.participant.dto.ParticipantDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.member.participant.entity.Participants;
import youth.week6.member.participant.mapper.ParticipantsToParticipantsDtoMapper;
import youth.week6.member.participant.repository.AllergensRepository;
import youth.week6.member.participant.repository.ParticipantsRepository;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantsRepository participantsRepository;
    private final AllergensRepository allergensRepository;
    private final ParticipantsToParticipantsDtoMapper dtoMapper;

    @Transactional
    public ParticipantDto join(ParticipantJoinDto participantJoinDto) {
        List<Allergens> allergens = allergensRepository.findAllById(
            participantJoinDto.getAllergens());
        Participants participants = new Participants(participantJoinDto.getDescription(),
            allergens);

        participantsRepository.save(participants);

        return dtoMapper.to(participants);
    }

    @Transactional(readOnly = true)
    public ParticipantDto get(long participantId) {
        Participants participants = participantsRepository.findById(participantId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "there is no participant info with " + participantId)
            );

        return dtoMapper.to(participants);
    }
}
