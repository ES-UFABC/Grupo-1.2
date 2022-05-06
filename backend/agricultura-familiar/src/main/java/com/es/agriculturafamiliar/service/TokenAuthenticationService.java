package com.es.agriculturafamiliar.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.agriculturafamiliar.entity.Administrador;
import com.es.agriculturafamiliar.entity.AuthenticatedUser;
import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.enums.RoleType;
import com.es.agriculturafamiliar.event.RenovacaoCodigoConfirmacaoEvent;
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
    private final ConfirmacaoCadastroService confirmacaoCadastroService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AdministradorService administradorService;

    /**
     * Authenticates and returns a token, otherwise it throws an exception if the user's account is not enabled or credentials aren't valid
     * @param user UserDetails, containing it's credentials
     * @return JWT token
     */
    public JwtToken authenticate(User user) throws UsernameNotFoundException, AuthException {
        User userDetails = (User) userDetailsManager.loadUserByUsername(user.getEmail());
        
        authenticationValidators.forEach(validator -> validator.validate(user, userDetails));
        
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
    
	
    @Transactional
    public void regenerateConfirmationCode(String email) throws AuthException, UsernameNotFoundException {
		User user = (User) userDetailsManager.loadUserByUsername(email);
		
		if (user.getConfirmacaoCadastro() == null || user.isEnabled()) {
			log.info("Usuário não necessita de confirmação de conta");			
			throw new AccountConfirmationNotRequiredException();	
		}
		
		ConfirmacaoCadastro confirmacaoCadastro = user.getConfirmacaoCadastro();
		String codigoConfirmacacao = confirmacaoCadastroService.generateCodigoConfirmacacao();
		confirmacaoCadastro.setCodigo(codigoConfirmacacao);
		confirmacaoCadastroService.save(confirmacaoCadastro);		
		
		publishRenovacaoCodigoConfirmacaoEvent(user, codigoConfirmacacao);
		
		log.info("Código de confirmação recriado com sucesso");
	}

	private void publishRenovacaoCodigoConfirmacaoEvent(User user, String codigoConfirmacacao) {
		RenovacaoCodigoConfirmacaoEvent renovacaoCodigoConfirmacaoEvent = RenovacaoCodigoConfirmacaoEvent.builder()
			.codigoConfirmacao(codigoConfirmacacao)
			.toEmail(user.getEmail())			
			.build();
		applicationEventPublisher.publishEvent(renovacaoCodigoConfirmacaoEvent);
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
        
        if (RoleType.ADMIN.equals(role.getRole())) {
        	Administrador administrador = administradorService.findByUserId(user.getId());
        	return String.valueOf(administrador.getId());
        }

        return String.valueOf(user.getId());
    }
    
}
