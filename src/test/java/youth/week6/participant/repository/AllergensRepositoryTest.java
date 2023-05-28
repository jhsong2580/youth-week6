package youth.week6.participant.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static youth.week6.testUtils.AllergensFixture.BEEF;
import static youth.week6.testUtils.AllergensFixture.BUCKWHEAT;
import static youth.week6.testUtils.AllergensFixture.CHICKEN;
import static youth.week6.testUtils.AllergensFixture.EGG;
import static youth.week6.testUtils.AllergensFixture.MACKEREL;
import static youth.week6.testUtils.AllergensFixture.MUSSEL;
import static youth.week6.testUtils.AllergensFixture.OYSTERS;
import static youth.week6.testUtils.AllergensFixture.PEACH;
import static youth.week6.testUtils.AllergensFixture.PEANUT;
import static youth.week6.testUtils.AllergensFixture.SEASHELL;
import static youth.week6.testUtils.AllergensFixture.SHRIMP;
import static youth.week6.testUtils.AllergensFixture.SOYBEAN;
import static youth.week6.testUtils.AllergensFixture.TOMATO;
import static youth.week6.testUtils.AllergensFixture.WALNUT;
import static youth.week6.testUtils.AllergensFixture.WHEAT;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import youth.week6.participant.entity.Allergens;
import youth.week6.participant.entity.Participants;
import youth.week6.testUtils.AllergensFixture;
import youth.week6.testUtils.SpringBootTestHelper;

class AllergensRepositoryTest extends SpringBootTestHelper {

    @Autowired
    private ParticipantsRepository participantsRepository;
    private Map<AllergensFixture, Allergens> allergens;

    @BeforeEach
    public void init() {
        super.init();
        savedAllergens();
        allergens = findAllAllergens();
    }

    @ParameterizedTest
    @MethodSource("provideAllergensFixtures")
    @DisplayName("Participants allergens update 테스트")
    public void test2(List<AllergensFixture> oldAllergensFixture,
        List<AllergensFixture> newAllergensFixture) {
        //given
        List<Allergens> oldAllergens = oldAllergensFixture.stream()
            .map(allergens::get)
            .collect(Collectors.toList());
        List<Allergens> newAllergens = newAllergensFixture.stream()
            .map(allergens::get)
            .collect(Collectors.toList());

        Participants participants = new Participants("description", oldAllergens);
        participantsRepository.save(participants);

        //when
        participants.updateAllergens(newAllergens);

        //then
        assertThat(participants.getAllergensInfo().getParticipantsAllergens())
            .extracting("allergens")
            .containsExactlyInAnyOrderElementsOf(newAllergens);
    }
    private static Stream<Arguments> provideAllergensFixtures() {
        return Stream.of(
            Arguments.of(Arrays.asList(BUCKWHEAT, WHEAT), Arrays.asList(WHEAT, SOYBEAN)),
            Arguments.of(Arrays.asList(SOYBEAN, WALNUT, PEANUT), Arrays.asList(WALNUT, PEANUT)),
            Arguments.of(Arrays.asList(PEACH, TOMATO), Arrays.asList(WALNUT, PEANUT, PEACH)),
            Arguments.of(Arrays.asList(CHICKEN, MACKEREL, OYSTERS),
                Arrays.asList(EGG, BEEF, SHRIMP, MUSSEL, OYSTERS)),
            Arguments.of(Arrays.asList(CHICKEN, MACKEREL, OYSTERS),
                Arrays.asList(EGG, BEEF, SHRIMP, MUSSEL, OYSTERS, SEASHELL))
        );
    }
}