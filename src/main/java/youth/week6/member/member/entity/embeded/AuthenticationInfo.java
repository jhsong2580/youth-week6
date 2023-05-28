package youth.week6.member.member.entity.embeded;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AuthenticationInfo {

    @Column(unique = true, nullable = false)
    private String identification;
    @Column(nullable = false)
    private String password;

    public AuthenticationInfo(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthenticationInfo)) {
            return false;
        }
        AuthenticationInfo that = (AuthenticationInfo) o;
        return Objects.equals(identification, that.identification)
            && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, password);
    }
}
