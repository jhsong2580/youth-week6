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
import youth.week6.member.member.dto.MemberJoinDto;
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

    public static Members from(MemberJoinDto memberJoinDto) {
        MemberInfo memberInfo = new MemberInfo(
            memberJoinDto.getName(),
            memberJoinDto.getBirthDate(),
            memberJoinDto.getSex(),
            memberJoinDto.getEmail()
        );
        AuthenticationInfo authenticationInfo = new AuthenticationInfo(
            memberJoinDto.getIdentification(), memberJoinDto.getPassword());

        return new Members(memberInfo, authenticationInfo);
    }

    private Members(MemberInfo memberInfo, AuthenticationInfo authenticationInfo) {
        assert memberInfo != null;
        assert authenticationInfo != null;
        this.memberInfo = memberInfo;
        this.authenticationInfo = authenticationInfo;
    }

    public void mappingParticipantsInfo(long participantsId) {
        if(this.authenticationInfo.hasRole(MemberRoles.PARTICIPANT)) {
            throw new IllegalArgumentException("member " + this.id + "already map participantInfo");
        }

        this.participantsId = participantsId;
        this.authenticationInfo.addRole(MemberRoles.PARTICIPANT);
    }

    public void mappingOrganizerInfo(long organizerId) {
        if(this.authenticationInfo.hasRole(MemberRoles.ORGANIZER)) {
            throw new IllegalArgumentException("member " + this.id + "already map organizerInfo");
        }

        this.organizerId = organizerId;
        this.authenticationInfo.addRole(MemberRoles.ORGANIZER);
    }

    public void unMappingParticipantsInfo() {
        this.participantsId = null;
        this.authenticationInfo.removeRole(MemberRoles.PARTICIPANT);
    }

    public void unMappingOrganizerInfo() {
        this.organizerId = null;
        this.authenticationInfo.removeRole(MemberRoles.ORGANIZER);
    }
}
