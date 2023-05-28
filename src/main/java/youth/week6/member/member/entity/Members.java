package youth.week6.member.member.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.member.entity.embeded.AuthenticationInfo;
import youth.week6.member.member.entity.embeded.MemberInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Members {

    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Embedded
    private MemberInfo memberInfo;

    @Embedded
    private AuthenticationInfo authenticationInfo;

    private Long participantsId;

    private Long organizerId;

    public Members(MemberInfo memberInfo, AuthenticationInfo authenticationInfo) {
        assert memberInfo != null;
        assert authenticationInfo != null;
        this.memberInfo = memberInfo;
        this.authenticationInfo = authenticationInfo;
    }

    public void mappingParticipantsInfo(long participantsId) {
        this.participantsId = participantsId;
    }

    public void mappingOrganizerInfo(long organizerId) {
        this.organizerId = organizerId;
    }

    public void unMappingParticipantsInfo() {
        this.participantsId = null;
    }

    public void unMappingOrganizerInfo() {
        this.organizerId = null;
    }
}
