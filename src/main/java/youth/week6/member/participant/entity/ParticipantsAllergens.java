package youth.week6.member.participant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ParticipantsAllergens {

    @Column(name = "participants_allergens_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participants_id", foreignKey = @ForeignKey(name = "FK_participants_allergens_participants"))
    private Participants participants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allergens_id", foreignKey = @ForeignKey(name = "FK_participants_allergens_allergens"))
    private Allergens allergens;

    public ParticipantsAllergens(Participants participants, Allergens allergens) {
        this.participants = participants;
        this.allergens = allergens;
    }
}
