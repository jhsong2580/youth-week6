package youth.week6.member.member.entity.embeded;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.member.entity.Sex;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberInfo {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime birthDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(nullable = false)
    private String email;

    public MemberInfo(String name, LocalDateTime birthDate, Sex sex, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberInfo)) {
            return false;
        }
        MemberInfo that = (MemberInfo) o;
        return Objects.equals(name, that.name) && Objects.equals(birthDate,
            that.birthDate) && sex == that.sex && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, sex, email);
    }
}
