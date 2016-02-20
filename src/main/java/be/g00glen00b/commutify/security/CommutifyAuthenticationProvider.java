package be.g00glen00b.commutify.security;

import be.g00glen00b.commutify.entity.CommutifyProfile;
import be.g00glen00b.commutify.repository.CommutifyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

public class CommutifyAuthenticationProvider implements AuthenticationProvider {
    private final List<SimpleGrantedAuthority> memberAuthorities = Collections.singletonList(new SimpleGrantedAuthority("member"));
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommutifyProfileRepository repository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final CommutifyProfile profile = repository.findByEmail(email);
        if (profile != null) {
            final String password = authentication.getCredentials().toString();
            final String hash = profile.getPassword();
            if (passwordEncoder.matches(password, hash)) {
                return new UsernamePasswordAuthenticationToken(email, authentication.getCredentials(), memberAuthorities);
            }
        }
        throw new UsernameNotFoundException("Username/Password invalid");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
