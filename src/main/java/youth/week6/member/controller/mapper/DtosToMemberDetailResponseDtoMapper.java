package youth.week6.member.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.ObjectUtils;
import youth.week6.member.dto.response.AllergenResponseDto;
import youth.week6.member.dto.response.MemberDetailResponseDto;
import youth.week6.member.dto.response.MemberResponseDto;
import youth.week6.member.dto.response.OrganizerResponseDto;
import youth.week6.member.dto.response.ParticipantResponseDto;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.organizer.dto.OrganizerDto;
import youth.week6.member.participant.dto.ParticipantDto;

@Mapper(componentModel = "spring")
public interface DtosToMemberDetailResponseDtoMapper {

    @Mapping(expression = "java(getMemberResponseDto(memberDto))", target = "member")
    @Mapping(expression = "java(getParticipantResponseDto(participantDto))", target = "participant")
    @Mapping(expression = "java(getOrganizerResponseDto(organizerDto))", target = "organizer")
    MemberDetailResponseDto to(
        MemberDto memberDto,
        ParticipantDto participantDto,
        OrganizerDto organizerDto
    );




    default ParticipantResponseDto getParticipantResponseDto(ParticipantDto participantDto) {
        if(ObjectUtils.isEmpty(participantDto)) {
            return null;
        }

        List<AllergenResponseDto> allergenResponseDtos = participantDto.getAllergens().stream()
            .map(
                allergenDto -> new AllergenResponseDto(allergenDto.getId(), allergenDto.getName()))
            .collect(Collectors.toList());

        return new ParticipantResponseDto(
            participantDto.getId(),
            participantDto.getDescription(),
            allergenResponseDtos
        );
    }

    default OrganizerResponseDto getOrganizerResponseDto(OrganizerDto organizerDto) {
        if(ObjectUtils.isEmpty(organizerDto)) {
            return null;
        }

        return new OrganizerResponseDto(
            organizerDto.getId(),
            organizerDto.getBelong()
        );
    }

    default MemberResponseDto getMemberResponseDto(MemberDto memberDto) {
        return new MemberResponseDto(
            memberDto.getId(),
            memberDto.getName(),
            memberDto.getBirthDate(),
            memberDto.getSex(),
            memberDto.getEmail(),
            memberDto.getIdentification()
        );
    }
}
