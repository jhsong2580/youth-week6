package youth.week6.member.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import youth.week6.member.member.entity.Sex;

@Getter
public class MemberResponseDto {

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
