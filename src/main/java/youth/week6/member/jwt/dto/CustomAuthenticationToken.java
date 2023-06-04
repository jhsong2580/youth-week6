package youth.week6.member.jwt.dto;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private long memberId;
    private String identification;
    private String password;
    public CustomAuthenticationToken(String identification, String password, long memberId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.memberId = memberId;
        this.identification = identification;
        this.password = password;
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.identification;
    }

    public long getMemberId() {
        return memberId;
    }
}