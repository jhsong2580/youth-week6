package youth.week6.member.organizer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.dto.OrganizerUpdateDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Organizers {

    @Column(name = "organizers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String belong;

    public Organizers(String belong) {
        this.belong = belong;
    }

    public void update(OrganizerUpdateDto organizerUpdateDto) {
        this.belong = organizerUpdateDto.getBelong();
    }
}
