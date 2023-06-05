package youth.week6.member.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import youth.week6.member.dto.request.MemberJoinRequestDto;
import youth.week6.member.member.dto.MemberJoinDto;

@Mapper(componentModel = "spring")
public interface MemberJoinRequestDtoToJoinDtoMapper {

    @Mapping(source = "localDateTimeBirthDate", target = "birthDate")
    MemberJoinDto to(MemberJoinRequestDto memberJoinRequestDto);
}
