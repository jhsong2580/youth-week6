package youth.week6.member.organizer.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import youth.week6.member.organizer.dto.OrganizerDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;
import youth.week6.member.organizer.repository.OrganizersRepository;

@ExtendWith(MockitoExtension.class)
class OrganizerServiceTest {

    private OrganizerService organizerService;

    @Mock
    private OrganizersRepository organizersRepository;

    @BeforeEach
    public void init() {
        organizerService = new OrganizerService(organizersRepository);
    }

    @Test
    @DisplayName("주최자 정보 설정")
    public void test1() {
        //given
        OrganizerJoinDto organizerJoinDto = new OrganizerJoinDto("belong");

        //when
        OrganizerDto organizerDto = organizerService.join(organizerJoinDto);

        //then
        assertThat(organizerDto.getBelong()).isEqualTo(organizerJoinDto.getBelong());
    }
}
