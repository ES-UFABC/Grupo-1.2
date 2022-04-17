package com.es.agriculturafamiliar.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.AuthenticatedUser;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.AccountConfirmationNotRequiredException;
import com.es.agriculturafamiliar.exception.AuthException;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import com.es.agriculturafamiliar.service.validator.authentication.IAuthenticationValidator;
import com.es.agriculturafamiliar.service.validator.authentication.confirmation.IAccountConfirmationValidator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TokenAuthenticationService {
    private final ICustomUserDetailsService<User> userDetailsManager;
    private final ITokenService tokenService;
    private final ProdutorService produtorService;
    private final CadastroConsumidorUseCase consumidorService;
    private final List<IAuthenticationValidator<User, User>> authenticationValidators;
    private final List<IAccountConfirmationValidator> accountConfirmationValidators;

    /**
     * Authenticates and returns a token, otherwise it throws an exception if the user's account is not enabled or credentials aren't valid
     * @param user UserDetails, containing it's credentials
     * @return JWT token
     */
    public JwtToken authenticate(User user) throws UsernameNotFoundException, AuthException {
        User userDetails = (User) userDetailsManager.loadUserByUsername(user.getEmail());
        
        authenticationValidators.stream().forEach(validator -> validator.validate(user, userDetails));
        
        JwtToken generatedToken = tokenService.generateToken(userDetails);

        String specificId = getSpecificId(userDetails);
        
        AuthenticatedUser authenticatedUser =  AuthenticatedUser.builder()            
            .roles(userDetails.getRoles())
            .id(specificId)
            .build();
        generatedToken.setUser(authenticatedUser);
        
        log.info("Usuário autenticado com sucesso, retornando token");

        return generatedToken;
    }
    
    public void enableAccount(String email, String token) throws UsernameNotFoundException, AuthException {
    	User user = (User) userDetailsManager.loadUserByUsername(email);
    	
		if (user.getConfirmacaoCadastro() == null || user.isEnabled()) {
			log.info("Usuário não necessita de confirmação de conta");			
			throw new AccountConfirmationNotRequiredException();	
		}	
    	
    	accountConfirmationValidators.forEach(validator -> validator.validate(user, token));
    	userDetailsManager.enableUser(user);
    }

    public void signUp(User user) {
        userDetailsManager.createUser(user);
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
