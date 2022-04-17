package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.InvalidCredentialsException;
import com.es.agriculturafamiliar.service.validator.authentication.CredentialsValidator;
import com.es.agriculturafamiliar.service.validator.authentication.IAuthenticationValidator;
import com.es.agriculturafamiliar.service.validator.authentication.UserEnabledValidator;
import com.es.agriculturafamiliar.service.validator.authentication.confirmation.IAccountConfirmationValidator;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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
    
    @Mock
    private List<IAuthenticationValidator<User, User>> authenticationValidators;
  

    @Mock
    private  List<IAccountConfirmationValidator> accountConfirmationValidators;
        

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
        when(tokenService.generateToken(any())).thenReturn(JwtToken.builder().token("token").expirationDate(Instant.now().toString()).build());

        JwtToken jwtToken = tokenAuthenticationService.authenticate(user);
        assertNotNull(jwtToken);
        assertNotNull(jwtToken.getToken());
        assertNotNull(jwtToken.getExpirationDate());
    }


    @Test
    public void signUp_shouldProperlyCallUserManagerService_whenUserIsPassed() {
        tokenAuthenticationService.signUp(user);
        verify(userDetailsManager, times(1)).createUser(user);
    }
}
