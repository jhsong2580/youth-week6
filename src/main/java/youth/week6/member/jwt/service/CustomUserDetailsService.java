package youth.week6.member.jwt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import youth.week6.member.jwt.dto.CustomUserDetails;
import youth.week6.member.member.entity.Members;
import youth.week6.member.member.entity.embeded.AuthenticationInfo;
import youth.week6.member.member.repository.MembersRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final MembersRepository membersRepository;
    private final GrantedAuthorityDefaults grantedAuthorityDefaults;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetails loadUserByUsername(String username) {
        Optional<Members> membersOptional = membersRepository.findByAuthenticationInfo_Identification(
            username);

        if (membersOptional.isEmpty()) {
            return null;
        }

        return getUserDetailsByMembers(membersOptional.get());
    }

    private CustomUserDetails getUserDetailsByMembers(Members members) {
        AuthenticationInfo authenticationInfo = members.getAuthenticationInfo();

        List<SimpleGrantedAuthority> roles = authenticationInfo.getRoles()
            .stream()
            .map((memberRoles) -> new SimpleGrantedAuthority(
                grantedAuthorityDefaults.getRolePrefix() + memberRoles.name())
            )
            .collect(Collectors.toList());

        return new CustomUserDetails(
            members.getId(),
            authenticationInfo.getIdentification(),
            passwordEncoder.encode(authenticationInfo.getPassword()),
            roles
        );
    }
}