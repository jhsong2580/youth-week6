package youth.week6.member.controller.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor // Constructor For Acceptance Test - MemberControllerTest
public class ParticipantJoinRequestDto {
    private String description; //자기소개
    private List<Long> allergens;
}
