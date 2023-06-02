package youth.week6.member.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youth.week6.member.dto.request.OrganizerMemberJoinRequestDto;
import youth.week6.member.dto.request.ParticipantMemberJoinRequestDto;
import youth.week6.member.dto.response.MemberJoinResponseDto;
import youth.week6.member.mapper.MemberJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.mapper.OrganizerJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.mapper.ParticipantJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;
import youth.week6.member.participant.service.ParticipantService;
import youth.week6.member.service.MemberFacadeService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacadeService memberFacadeService;
    private final MemberJoinRequestDtoToJoinDtoMapper memberJoinDtoMapper;
    private final OrganizerJoinRequestDtoToJoinDtoMapper organizerJoinDtoMapper;
    private final ParticipantJoinRequestDtoToJoinDtoMapper participantJoinDtoMapper;

    private final ParticipantService participantService;

    @PostMapping("/participants")
    public ResponseEntity<?> join(
        @Validated @RequestBody ParticipantMemberJoinRequestDto participantMemberJoinRequestDto,
        BindingResult bindingResult
    ) {

        // TODO: 2023/06/01 bindingResult Error Handling
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        MemberJoinDto memberJoinDto = memberJoinDtoMapper.to(
            participantMemberJoinRequestDto.getMember());
        ParticipantJoinDto participantJoinDto = participantJoinDtoMapper.to(
            participantMemberJoinRequestDto.getParticipant());

        long memberId = memberFacadeService.join(memberJoinDto, participantJoinDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberJoinResponseDto(memberId));

    }

    @PostMapping("/organizers")
    public ResponseEntity<?> join(
        @Validated @RequestBody OrganizerMemberJoinRequestDto organizerMemberJoinRequestDto,
        BindingResult bindingResult
    ) {

        // TODO: 2023/06/01 bindingResult Error Handling
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        MemberJoinDto memberJoinDto = memberJoinDtoMapper.to(
            organizerMemberJoinRequestDto.getMember());
        OrganizerJoinDto organizerJoinDto = organizerJoinDtoMapper.to(
            organizerMemberJoinRequestDto.getOrganizer());

        long memberId = memberFacadeService.join(memberJoinDto, organizerJoinDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberJoinResponseDto(memberId));

    }
}
