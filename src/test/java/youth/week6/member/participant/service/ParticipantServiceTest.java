package youth.week6.member.participant.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static youth.week6.testFixture.AllergensFixture.BEEF;
import static youth.week6.testFixture.AllergensFixture.MILK;

import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import youth.week6.member.participant.dto.ParticipantDto;
import youth.week6.member.dto.ParticipantJoinDto;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.testFixture.AllergensFixture;
import youth.week6.testUtils.SpringBootTestHelper;

class ParticipantServiceTest extends SpringBootTestHelper {

    @Autowired
    private ParticipantService participantService;
    private Map<AllergensFixture, Allergens> allAllergens;
    @BeforeEach
    public void init() {
        super.init();
        super.savedAllergens();
        allAllergens = super.findAllAllergens();
    }

    @Test
    @DisplayName("참여자 정보 등록")
    public void test1 (){
        //given
        ParticipantJoinDto participantJoinDto = new ParticipantJoinDto(
            Arrays.asList(
                allAllergens.get(BEEF).getId(),
                allAllergens.get(MILK).getId()
            ), "description"
        );


        //when
        ParticipantDto participantDto = participantService.join(participantJoinDto);

        //then
        assertAll(
            () -> assertThat(participantDto.getDescription()).isEqualTo("description"),
            () -> assertThat(participantDto.getId()).isEqualTo(1L),
            () -> assertThat(participantDto.getAllergens()).extracting("name")
                .containsExactlyInAnyOrderElementsOf(
                    Arrays.asList(
                        BEEF.name(), MILK.name()
                    )
                )
        );

    }
}