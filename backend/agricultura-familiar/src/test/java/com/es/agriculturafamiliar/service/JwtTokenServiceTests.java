package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AuthException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtTokenServiceTests {
    @InjectMocks
    private ITokenService tokenService = new JwtTokenService("cebola", 32L);

    private static final User user = User.builder()
        .email("myemail@email.com")
        .enabled(true)
        .password("12345")
        .id(1L)
        .roles(Set.of(Role.builder().role(RoleType.ADMIN).build()))     
        .build();

    @Test
    public void generateToken_shouldGenerateAndReturnJwtToken_WhenProperUserDetailsIsSent() {

        JwtToken generatedToken = tokenService.generateToken(user);

        assertNotNull(generatedToken);
        assertNotNull(generatedToken.getToken());
        assertNotNull(generatedToken.getExpirationDate());
    }

    @Test
    public void getUsernameFromToken_shouldReturnCorrespondingUser_whenValidTokenIsProvided() {
        JwtToken generatedToken = tokenService.generateToken(user);

        String usernameFromToken = tokenService.getUsernameFromToken(generatedToken.getToken());
        assertEquals(user.getEmail(), usernameFromToken);
    }

    @Test
    public void isTokenValid_shouldReturnTrue_whenUsernameMatchesAndTokenIsNotExpired() {


        JwtToken generatedToken = tokenService.generateToken(user);
        assertTrue(() -> tokenService.isTokenValid(generatedToken.getToken(), user));
    }

    @Test
    public void isTokenValid_shouldReturnFalse_whenUsernameDoesNotMatch() {
        User inputUser = User.builder()
        .email("moutro@email.com")
        .enabled(true)
        .password("12345")
        .id(1L)
        .roles(Set.of(Role.builder().role(RoleType.ADMIN).build()))          
        .build();

        JwtToken generatedToken = tokenService.generateToken(user);

        assertThrows(AuthException.class, () -> tokenService.isTokenValid(generatedToken.getToken(), inputUser));
    }
}
