package youth.week6.member.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import youth.week6.member.member.entity.Sex;

@Getter
public class MemberDetailResponseDto {

    private final MemberResponseDto member;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ParticipantResponseDto participant;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final OrganizerResponseDto organizer;

    @Builder
    public MemberDetailResponseDto(MemberResponseDto member, ParticipantResponseDto participant,
        OrganizerResponseDto organizer) {
        this.member = member;
        this.participant = participant;
        this.organizer = organizer;
    }

    @Getter
    public static class ParticipantResponseDto {
        private final Long id;
        private final String description;
        private final List<AllergenResponseDto> allergens;

        public ParticipantResponseDto(Long id, String description, List<AllergenResponseDto> allergens) {
            this.id = id;
            this.description = description;
            this.allergens = allergens;
        }
    }

    @Getter
    public static class MemberResponseDto {

        private final Long id;
        private final String name;
        private final LocalDateTime birthDate;
        private final Sex sex;
        private final String email;
        private final String identification;

        @Builder
        public MemberResponseDto(Long id, String name, LocalDateTime birthDate, Sex sex, String email,
            String identification) {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
            this.sex = sex;
            this.email = email;
            this.identification = identification;
        }
    }

    @Getter
    public static class OrganizerResponseDto {
        private final Long id;
        private final String belong;

        @Builder
        public OrganizerResponseDto(Long id, String belong) {
            this.id = id;
            this.belong = belong;
        }
    }

    @Getter
    public static class AllergenResponseDto {
        private final Long id;
        private final String name;

        public AllergenResponseDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
