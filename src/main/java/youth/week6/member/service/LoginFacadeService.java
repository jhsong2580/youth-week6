package youth.week6.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.week6.member.jwt.dto.CustomAuthenticationToken;
import youth.week6.member.jwt.dto.TokenInfo;
import youth.week6.member.jwt.service.CustomAuthenticationProvider;
import youth.week6.member.jwt.service.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class LoginFacadeService {

    private final CustomAuthenticationProvider authenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(String identification, String password) {

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(identification,
            password);

        CustomAuthenticationToken authentication = authenticationProvider.authenticate(
            authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, identification);

        return tokenInfo;
    }
}
