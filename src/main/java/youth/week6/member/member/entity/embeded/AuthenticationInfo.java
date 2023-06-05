package youth.week6.member.member.entity.embeded;

import java.util.EnumSet;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.member.entity.MemberRoles;
import youth.week6.member.member.entity.utils.MemberRolesConverter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AuthenticationInfo {

    @Column(unique = true, nullable = false)
    private String identification;
    @Column(nullable = false)
    private String password;

    @Convert(converter = MemberRolesConverter.class)
    private EnumSet<MemberRoles> roles = EnumSet.noneOf(MemberRoles.class);

    public AuthenticationInfo(String identification, String password) {
        this.identification = identification;
        this.password = password;
        this.roles = EnumSet.noneOf(MemberRoles.class);
        roles.add(MemberRoles.USER);
    }

    public void addRole(MemberRoles memberRoles) {
        roles.add(memberRoles);
    }

    public void removeRole(MemberRoles memberRoles) {
        roles.remove(memberRoles);
    }

    public boolean hasRole(MemberRoles memberRoles) {
        return roles.contains(memberRoles);
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
