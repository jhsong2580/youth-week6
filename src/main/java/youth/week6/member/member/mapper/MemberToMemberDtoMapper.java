package youth.week6.member.member.mapper;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import youth.week6.member.member.dto.MemberDto;
import youth.week6.member.member.entity.Members;
import youth.week6.member.member.entity.Sex;

@Mapper(componentModel = "spring")
@SuppressWarnings("password")
public interface MemberToMemberDtoMapper {

    @Mapping(source = "memberInfo.name", target = "name")
    @Mapping(source = "memberInfo.birthDate", target = "birthDate")
    @Mapping(source = "memberInfo.sex", target = "sex")
    @Mapping(source = "memberInfo.email", target = "email")
    @Mapping(source = "authenticationInfo.identification", target = "identification")
    MemberDto to(Members members);
}
