package com.es.agriculturafamiliar.service;

import java.util.Set;
import java.util.stream.Collectors;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class JwtUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String NOT_FOUND_USER_MESSAGE = "Usuário com o email fornecido não encontrado";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {        
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER_MESSAGE));
        log.info("Usuário com email {} e id: {} encontrado", user.getEmail(), user.getId());
        return user;
    }

    @Override
    public void createUser(UserDetails user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        
        Set<Role> roles = user.getAuthorities()
            .stream()
            .map(b -> Role.builder().role(RoleType.valueOfIgnoreCase(b.getAuthority())).build())
            .collect(Collectors.toSet());

        User userToBePersisted = User.builder()
            .email(user.getUsername())
            .password(encodedPassword)
            .enabled(true)
            .roles(roles)
            .build();

        User persistedUser = userRepository.save(userToBePersisted);
        log.info("Usuário de id {} e email {} persistido com sucesso", persistedUser.getId(), persistedUser.getEmail());
    }

    @Override
    public void updateUser(UserDetails user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteUser(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean userExists(String username) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
