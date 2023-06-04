package youth.week6.member.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDto {
    @NotEmpty
    private String identification;
    @NotEmpty
    private String password;
}
