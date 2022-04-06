package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.entity.User;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TokenAuthenticationService {
    private final ICustomUserDetailsService<User> userDetailsManager;
    private final ITokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Authenticates and returns a token, otherwise it throws an exception if the user's account is not enabled or credentials aren't valid
     * @param user UserDetails, containing it's credentials
     * @return JWT token
     */
    public String authenticate(User user) {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(user.getEmail());
        boolean isCredentialValid = validateCredentials(user, userDetails);

        if (!isCredentialValid) {
            log.info("Credenciais fornecidas para o usuário {} são inválidas");
            throw new BadCredentialsException("Credenciais inválidas");
        }

        log.info("Credenciais autenticadas com sucesso, retornando token");
        
        return tokenService.generateToken(userDetails);
    }

    public void signUp(User user) {
        userDetailsManager.createUser(user);
    }


    private boolean validateCredentials(User user, UserDetails userDetails) {
        if (user == null || userDetails == null) {
            return false;
        }
        return passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

    }
    
}
