package youth.week6.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youth.week6.argumentResolver.MemberId;
import youth.week6.member.controller.dto.request.join.OrganizerJoinRequestDto;
import youth.week6.member.controller.dto.request.join.OrganizerMemberJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantJoinRequestDto;
import youth.week6.member.controller.dto.request.join.ParticipantMemberJoinRequestDto;
import youth.week6.member.controller.dto.request.login.LoginRequestDto;
import youth.week6.member.controller.dto.request.update.MemberDetailUpdateRequestDto;
import youth.week6.member.controller.dto.response.MemberDetailResponseDto;
import youth.week6.member.controller.dto.response.MemberJoinResponseDto;
import youth.week6.member.controller.mapper.DtosToMemberDetailResponseDtoMapper;
import youth.week6.member.controller.mapper.JoinDtoMapper;
import youth.week6.member.controller.mapper.UpdateDtoMapper;
import youth.week6.member.dto.MemberJoinDto;
import youth.week6.member.dto.MemberUpdateDto;
import youth.week6.member.dto.OrganizerJoinDto;
import youth.week6.member.dto.OrganizerUpdateDto;
import youth.week6.member.dto.ParticipantJoinDto;
import youth.week6.member.dto.ParticipantUpdateDto;
import youth.week6.member.jwt.dto.TokenInfo;
import youth.week6.member.service.LoginFacadeService;
import youth.week6.member.service.MemberFacadeService;
import youth.week6.member.service.dto.MemberDetailDto;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacadeService memberFacadeService;
    private final LoginFacadeService loginFacadeService;
    private final JoinDtoMapper memberJoinDtoMapper;
    private final UpdateDtoMapper updateDtoMapper;
    private final DtosToMemberDetailResponseDtoMapper detailResponseDtoMapper;

    @PostMapping("/participants")
    public ResponseEntity<?> join(
        @Validated @RequestBody ParticipantMemberJoinRequestDto participantMemberJoinRequestDto,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        MemberJoinDto memberJoinDto = memberJoinDtoMapper.to(
            participantMemberJoinRequestDto.getMember());
        ParticipantJoinDto participantJoinDto = memberJoinDtoMapper.to(
            participantMemberJoinRequestDto.getParticipant());

        long memberId = memberFacadeService.join(memberJoinDto, participantJoinDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberJoinResponseDto(memberId));

    }

    @PostMapping("/organizers")
    public ResponseEntity<?> join(
        @Validated @RequestBody OrganizerMemberJoinRequestDto organizerMemberJoinRequestDto,
        BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        MemberJoinDto memberJoinDto = memberJoinDtoMapper.to(
            organizerMemberJoinRequestDto.getMember());
        OrganizerJoinDto organizerJoinDto = memberJoinDtoMapper.to(
            organizerMemberJoinRequestDto.getOrganizer());

        long memberId = memberFacadeService.join(memberJoinDto, organizerJoinDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberJoinResponseDto(memberId));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Validated @RequestBody LoginRequestDto loginRequestDto,
        BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        TokenInfo tokenInfo = loginFacadeService.login(loginRequestDto.getIdentification(),
            loginRequestDto.getPassword());

        return ResponseEntity.ok(tokenInfo.getAccessToken());
    }

    @GetMapping()
    public ResponseEntity<?> retrieveMember(@MemberId Long memberId) {
        MemberDetailDto memberDetailDto = memberFacadeService.get(memberId);

        MemberDetailResponseDto memberDetailResponseDto = detailResponseDtoMapper.to(
            memberDetailDto.getMemberDto(),
            memberDetailDto.getParticipantDto(),
            memberDetailDto.getOrganizerDto()
        );

        return ResponseEntity.ok(memberDetailResponseDto);
    }

    @PatchMapping("/participants")
    public ResponseEntity<?> addRole(
        @Validated @RequestBody ParticipantJoinRequestDto participantJoinRequestDto,
        BindingResult bindingResult,
        @MemberId Long memberId
    ) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        ParticipantJoinDto participantJoinDto = memberJoinDtoMapper.to(participantJoinRequestDto);

        memberFacadeService.joinParticipant(memberId, participantJoinDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/organizers")
    public ResponseEntity<?> addRole(
        @Validated @RequestBody OrganizerJoinRequestDto organizerJoinRequestDto,
        BindingResult bindingResult,
        @MemberId Long memberId
    ) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        OrganizerJoinDto organizerJoinDto = memberJoinDtoMapper.to(organizerJoinRequestDto);

        memberFacadeService.joinOrganizer(memberId, organizerJoinDto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> update(
        @Validated @RequestBody MemberDetailUpdateRequestDto memberDetailUpdateRequestDto,
        BindingResult bindingResult,
        @MemberId Long memberId
    ) {

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

            throw new IllegalArgumentException(errorMessage);
        }

        MemberUpdateDto memberUpdateDto = updateDtoMapper.to(
            memberDetailUpdateRequestDto.getMember());
        OrganizerUpdateDto organizerUpdateDto = updateDtoMapper.to(
            memberDetailUpdateRequestDto.getOrganizer());
        ParticipantUpdateDto participantUpdateDto = updateDtoMapper.to(
            memberDetailUpdateRequestDto.getParticipant());

        memberFacadeService.update(memberId, memberUpdateDto, organizerUpdateDto,
            participantUpdateDto);

        return ResponseEntity.ok().build();
    }
}
