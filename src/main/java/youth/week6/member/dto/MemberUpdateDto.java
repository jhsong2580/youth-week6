package youth.week6.member.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import youth.week6.member.member.entity.Sex;

@Getter
@AllArgsConstructor
@Builder
public class MemberUpdateDto {
    private String name;
    private LocalDateTime birthDate;
    private Sex sex;
    private String identification;
    private String password;
    private String email;
}
