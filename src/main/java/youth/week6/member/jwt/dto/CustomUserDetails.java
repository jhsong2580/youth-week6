package youth.week6.member.jwt.dto;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUserDetails extends User {

    private final long memberId;

    public CustomUserDetails(long memberId, String identification, String password,
        Collection<? extends GrantedAuthority> roles) {
        super(identification, password, roles);
        this.memberId = memberId;
    }
}
