package youth.week6.member.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youth.week6.argumentResolver.MemberId;
import youth.week6.member.dto.request.LoginRequestDto;
import youth.week6.member.dto.request.OrganizerMemberJoinRequestDto;
import youth.week6.member.dto.request.ParticipantJoinRequestDto;
import youth.week6.member.dto.request.ParticipantMemberJoinRequestDto;
import youth.week6.member.dto.response.MemberDetailResponseDto;
import youth.week6.member.dto.response.MemberJoinResponseDto;
import youth.week6.member.jwt.dto.TokenInfo;
import youth.week6.member.controller.mapper.DtosToMemberDetailResponseDtoMapper;
import youth.week6.member.controller.mapper.MemberJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.controller.mapper.OrganizerJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.controller.mapper.ParticipantJoinRequestDtoToJoinDtoMapper;
import youth.week6.member.member.dto.MemberJoinDto;
import youth.week6.member.organizer.dto.OrganizerJoinDto;
import youth.week6.member.participant.dto.ParticipantJoinDto;
import youth.week6.member.service.LoginFacadeService;
import youth.week6.member.service.MemberFacadeService;
import youth.week6.member.service.dto.MemberDetailDto;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacadeService memberFacadeService;
    private final LoginFacadeService loginFacadeService;
    private final MemberJoinRequestDtoToJoinDtoMapper memberJoinDtoMapper;
    private final OrganizerJoinRequestDtoToJoinDtoMapper organizerJoinDtoMapper;
    private final ParticipantJoinRequestDtoToJoinDtoMapper participantJoinDtoMapper;
    private final DtosToMemberDetailResponseDtoMapper detailResponseDtoMapper;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Validated @RequestBody LoginRequestDto loginRequestDto,
        BindingResult bindingResult
    ) {

        // TODO: 2023/06/01 bindingResult Error Handling
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        TokenInfo tokenInfo = loginFacadeService.login(loginRequestDto.getIdentification(),
            loginRequestDto.getPassword());

        return ResponseEntity.ok(tokenInfo.getAccessToken());
    }

    @GetMapping()
    public ResponseEntity<?> retrieveMember (@MemberId Long memberId){
        MemberDetailDto memberDetailDto = memberFacadeService.get(memberId);

        MemberDetailResponseDto memberDetailResponseDto = detailResponseDtoMapper.to(
            memberDetailDto.getMemberDto(),
            memberDetailDto.getParticipantDto(),
            memberDetailDto.getOrganizerDto()
        );

        return ResponseEntity.ok(memberDetailResponseDto);
    }

    @PatchMapping("/participants")
    public ResponseEntity<?> login(
        @Validated @RequestBody ParticipantJoinRequestDto participantJoinRequestDto,
        BindingResult bindingResult,
        @MemberId Long memberId
    ) {

        // TODO: 2023/06/01 bindingResult Error Handling
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        ParticipantJoinDto participantJoinDto = participantJoinDtoMapper.to(participantJoinRequestDto);

        memberFacadeService.joinParticipant(memberId, participantJoinDto);

        return ResponseEntity.ok().build();
    }
}
