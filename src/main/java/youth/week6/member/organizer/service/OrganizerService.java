package youth.week6.member.organizer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.dto.OrganizerUpdateDto;
import youth.week6.member.organizer.dto.OrganizerDto;
import youth.week6.member.dto.OrganizerJoinDto;
import youth.week6.member.organizer.entity.Organizers;
import youth.week6.member.organizer.repository.OrganizersRepository;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizersRepository organizersRepository;

    @Transactional
    public OrganizerDto join(OrganizerJoinDto organizerJoinDto) {
        Organizers organizers = new Organizers(organizerJoinDto.getBelong());

        organizersRepository.save(organizers);

        return new OrganizerDto(organizers.getId(), organizerJoinDto.getBelong());
    }

    @Transactional(readOnly = true)
    public OrganizerDto get(long organizerId) {
        Organizers organizers = organizersRepository.findById(organizerId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "there is no organizer info with " + organizerId)
            );

        return new OrganizerDto(organizers.getId(), organizers.getBelong());
    }

    @Transactional
    public void update(Long organizerId, OrganizerUpdateDto organizerUpdateDto) {
        Organizers organizers = organizersRepository.findById(organizerId)
            .orElseThrow(
                () -> new IllegalArgumentException(
                    "there is no organizer info with " + organizerId)
            );

        organizers.update(organizerUpdateDto);
    }
}
