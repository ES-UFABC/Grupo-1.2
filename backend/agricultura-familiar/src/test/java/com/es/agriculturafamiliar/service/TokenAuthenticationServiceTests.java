package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Set;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class TokenAuthenticationServiceTests {

    @InjectMocks
    private TokenAuthenticationService tokenAuthenticationService;
    
    @Mock
    private ICustomUserDetailsService<User> userDetailsManager;

    @Mock
    private ITokenService tokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private static final User user = User.builder()
        .email("myemail@email.com")
        .enabled(true)
        .password("12345")
        .id(1L)
        .roles(Set.of(Role.builder().role(RoleType.ADMIN).build()))     
        .build();

    @Test
    public void authenticate_shouldReturnToken_whenUserExists() {
        when(userDetailsManager.loadUserByUsername(any(String.class))).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(tokenService.generateToken(any())).thenReturn(JwtToken.builder().token("token").expirationDate(Instant.now().toString()).build());

        JwtToken jwtToken = tokenAuthenticationService.authenticate(user);
        assertNotNull(jwtToken);
        assertNotNull(jwtToken.getToken());
        assertNotNull(jwtToken.getExpirationDate());
    }

    @Test
    public void autheticate_shouldThowBadCredentialsException_whenCredetialsDoNotMatch() {
        when(userDetailsManager.loadUserByUsername(any(String.class))).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(false);
        assertThrows(BadCredentialsException.class, () -> tokenAuthenticationService.authenticate(user));
    }

    @Test
    public void signUp_shouldProperlyCallUserManagerService_whenUserIsPassed() {
        tokenAuthenticationService.signUp(user);
        verify(userDetailsManager, times(1)).createUser(user);
    }
}
