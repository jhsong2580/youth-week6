package youth.week6.member.participant.entity.embedded;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.member.participant.entity.ParticipantsAllergens;
import youth.week6.member.participant.entity.Participants;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllergensInfo {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participants", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ParticipantsAllergens> participantsAllergens = new ArrayList<>();

    public AllergensInfo(Participants participants, List<Allergens> allergens) {
        participantsAllergens = allergens.stream()
            .map(allergen -> new ParticipantsAllergens(participants, allergen))
            .collect(Collectors.toList());
    }

    public void update(Participants participants, List<Allergens> allergens) {
        List<ParticipantsAllergens> newParticipantsAllergens = allergens.stream()
            .map(allergen -> new ParticipantsAllergens(participants, allergen))
            .collect(Collectors.toList());
        sortParticipantsAllergensListByAllergensId(this.participantsAllergens);
        sortParticipantsAllergensListByAllergensId(newParticipantsAllergens);

        List<ParticipantsAllergens> temp = deleteNotMatchAllergensAndGetNewAllergens(
            newParticipantsAllergens);

        participantsAllergens.addAll(temp);
    }

    private void sortParticipantsAllergensListByAllergensId(
        List<ParticipantsAllergens> participantsAllergens) {
        Collections.sort(participantsAllergens,
            (o1, o2) -> Long.compare(o1.getAllergens().getId(), o2.getAllergens().getId()));
    }

    private List<ParticipantsAllergens> deleteNotMatchAllergensAndGetNewAllergens(
        List<ParticipantsAllergens> newParticipantsAllergens) {
        int ord_index = 0;
        int new_index = 0;
        List<ParticipantsAllergens> temp = new LinkedList<>();

        while (participantsAllergens.size() != ord_index
            || newParticipantsAllergens.size() != new_index) {

            if (ord_index == participantsAllergens.size()) {
                temp.add(newParticipantsAllergens.get(new_index));
                new_index++;
                continue;
            }

            if (new_index == newParticipantsAllergens.size()) {
                ParticipantsAllergens savedParticipantsAllergen = participantsAllergens.get(
                    ord_index);
                participantsAllergens.remove(savedParticipantsAllergen);
                continue;
            }

            ParticipantsAllergens savedParticipantsAllergen = participantsAllergens.get(ord_index);
            Allergens savedAllergens = savedParticipantsAllergen.getAllergens();

            ParticipantsAllergens newParticipantsAllergen = newParticipantsAllergens.get(new_index);
            Allergens newAllergens = newParticipantsAllergen.getAllergens();

            if (savedAllergens.getId() == newAllergens.getId()) {
                ord_index++;
                new_index++;
                continue;
            }
            if (savedAllergens.getId() > newAllergens.getId()) {
                temp.add(newParticipantsAllergen);
                new_index++;
                continue;
            }
            if (savedAllergens.getId() < newAllergens.getId()) {
                participantsAllergens.remove(savedParticipantsAllergen);
            }
        }
        return temp;
    }

    public List<ParticipantsAllergens> getParticipantsAllergens() {
        return Collections.unmodifiableList(this.participantsAllergens);
    }

}
