package youth.week6.member.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TokenInfo {
 
    private String grantType;
    private String accessToken;
    private String refreshToken;
}