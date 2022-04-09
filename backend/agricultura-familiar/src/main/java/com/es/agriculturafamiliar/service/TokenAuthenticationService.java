package com.es.agriculturafamiliar.service;

import java.util.Set;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.AuthenticatedUser;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.InvalidCredentialsException;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final ProdutorService produtorService;
    private final CadastroConsumidorUseCase consumidorService;

    /**
     * Authenticates and returns a token, otherwise it throws an exception if the user's account is not enabled or credentials aren't valid
     * @param user UserDetails, containing it's credentials
     * @return JWT token
     */
    public JwtToken authenticate(User user) throws UsernameNotFoundException, InvalidCredentialsException {
        User userDetails = (User) userDetailsManager.loadUserByUsername(user.getEmail());
        boolean isCredentialValid = validateCredentials(user, userDetails);

        if (!isCredentialValid) {
            log.info("Credenciais fornecidas para o usuário são inválidas");
            throw new InvalidCredentialsException();
        }

        log.debug("user: {}", userDetails.toString());
        log.info("Credenciais autenticadas com sucesso, retornando token");
        
        JwtToken generatedToken = tokenService.generateToken(userDetails);

        String specificId = getSpecificId(userDetails);
        
        AuthenticatedUser authenticatedUser =  AuthenticatedUser.builder()            
            .roles(userDetails.getRoles())
            .id(specificId)
            .build();
        generatedToken.setUser(authenticatedUser);

        return generatedToken;
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

    private String getSpecificId(User user) {
        Role role = user.getRoles().stream().findFirst().get();

        if (RoleType.PRODUTOR.equals(role.getRole())) {
            var produtorOpt = produtorService.consultaPorUserId(user.getId());

            return String.valueOf(produtorOpt.get().getId());
        }

        if (RoleType.CONSUMIDOR.equals(role.getRole())) {
            var consumidorOpt = consumidorService.findByUserId(user.getId());
            return consumidorOpt.get().getCpf();
        }

        return String.valueOf(user.getId());
    }
    
}
