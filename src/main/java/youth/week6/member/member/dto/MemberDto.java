package youth.week6.member.member.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import youth.week6.member.member.entity.Sex;

@Getter
public class MemberDto {

    private final Long id;
    private final String name;
    private final LocalDateTime birthDate;
    private final Sex sex;
    private final String email;
    private final String identification;
    private final String password;
    private final Long participantsId;
    private final Long organizerId;

    @Builder
    public MemberDto(Long id, String name, LocalDateTime birthDate, Sex sex, String email,
        String identification, String password, Long participantsId, Long organizerId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.email = email;
        this.identification = identification;
        this.password = password;
        this.participantsId = participantsId;
        this.organizerId = organizerId;
    }


}
