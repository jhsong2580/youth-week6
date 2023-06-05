package youth.week6.member.participant.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.week6.member.participant.entity.embedded.AllergensInfo;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Participants {

    @Column(name = "participants_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Lob
    private String description;

    @Embedded
    private AllergensInfo allergensInfo;

    public Participants(String description, List<Allergens> allergens) {
        this.description = description;
        this.allergensInfo = new AllergensInfo(this, allergens);
    }

    public void update(List<Allergens> allergens, String description) {
        this.allergensInfo.update(this, allergens);
        this.description = description;
    }

    public List<Allergens> getAllergens() {
        return this.allergensInfo.getAllergens();
    }
}
