package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class JwtUserDetailsManagerTests {

    @InjectMocks
    private JwtUserDetailsManager jwtUserDetailsManager;

    @Mock
    private UserRepository userRepository;

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
    public void loadUserByUsername_shouldThrowUsernameNotFoundException_whenUserIfNotFound() {
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.empty());
        String userEmail = "email@email.com";

        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsManager.loadUserByUsername(userEmail));        
    }

    @Test
    public void loadUserByUsername_shouldReturnUser_whenUserIfFound() {
        String userEmail = "email@email.com";
        
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));

        UserDetails loadedUser = jwtUserDetailsManager.loadUserByUsername(userEmail);
        assertNotNull(loadedUser);
        assertEquals(user.getEmail(), loadedUser.getUsername());
        assertEquals(user.getPassword(), loadedUser.getPassword());
        assertEquals(user.getAuthorities(), loadedUser.getAuthorities());
    }
    
    @Test
    public void createUser_shouldReturnPersistedUser_whenProperUserDetailsAreProvided() {
        when(userRepository.save(any())).thenReturn(user);

        User createdUser = jwtUserDetailsManager.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getAuthorities(), createdUser.getAuthorities());       
    }
    
    @Test
    public void enableUser_shouldSetEnabledToTrueAndSave_whenReceivedUser() {
    	User toBeEnabledUser = User.builder().enabled(false).build();
    	
    	jwtUserDetailsManager.enableUser(toBeEnabledUser);
    	
    	verify(userRepository, times(1)).save(toBeEnabledUser);
    }
}
