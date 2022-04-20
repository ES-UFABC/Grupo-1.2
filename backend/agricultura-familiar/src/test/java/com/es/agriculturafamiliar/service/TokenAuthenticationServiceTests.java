package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.JwtToken;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.event.RenovacaoCodigoConfirmacaoEvent;
import com.es.agriculturafamiliar.exception.AccountConfirmationNotRequiredException;
import com.es.agriculturafamiliar.service.validator.authentication.IAuthenticationValidator;
import com.es.agriculturafamiliar.service.validator.authentication.confirmation.IAccountConfirmationValidator;

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
	private List<IAccountConfirmationValidator> accountConfirmationValidators;
	
	@Mock
	private ConfirmacaoCadastroService confirmacaoCadastroService;
	
	@Mock
	private ApplicationEventPublisher applicationEventPublisher;

	private static final User user = User.builder().email("myemail@email.com").enabled(true).password("12345").id(1L)
			.roles(Set.of(Role.builder().role(RoleType.ADMIN).build())).build();

	@Test
	public void authenticate_shouldReturnToken_whenUserExists() {
		when(userDetailsManager.loadUserByUsername(any(String.class))).thenReturn(user);
		when(tokenService.generateToken(any()))
				.thenReturn(JwtToken.builder().token("token").expirationDate(Instant.now().toString()).build());

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

	@Test
	public void enableAccount_shouldThrowAccountConfirmationNotRequiredException_whenUserDoesNotRequireConfirmation() {
		String email = "email@email.com";
		String token = "email@email.com";
		User confirmationNotRequiredUser = User.builder().build();
		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationNotRequiredUser);

		assertThrows(AccountConfirmationNotRequiredException.class,
				() -> tokenAuthenticationService.enableAccount(email, token));
	}

	@Test
	public void enableAccount_shouldThrowAccountConfirmationNotRequiredException_whenUserAccountIsEnabled() {
		String email = "email@email.com";
		String token = "email@email.com";
		User confirmationNotRequiredUser = User.builder().enabled(true).build();
		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationNotRequiredUser);

		assertThrows(AccountConfirmationNotRequiredException.class,
				() -> tokenAuthenticationService.enableAccount(email, token));
	}

	@Test
	public void enableAccount_should_whenUserAccountIsEnabled() {
		String email = "email@email.com";
		String token = "email@email.com";
		User confirmationRequiredUser = User.builder().confirmacaoCadastro(ConfirmacaoCadastro.builder().build())
				.enabled(false).build();

		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationRequiredUser);

		assertDoesNotThrow(() -> tokenAuthenticationService.enableAccount(email, token));
		verify(userDetailsManager, times(1)).enableUser(confirmationRequiredUser);
	}

	@Test
	public void regenerateConfirmationCode_shouldThrowAccountConfirmationNotRequiredException_whenUserDoesNotRequireConfirmation() {
		String email = "email@email.com";
		User confirmationNotRequiredUser = User.builder().build();
		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationNotRequiredUser);

		assertThrows(AccountConfirmationNotRequiredException.class,
				() -> tokenAuthenticationService.regenerateConfirmationCode(email));
	}

	@Test
	public void regenerateConfirmationCode_shouldThrowAccountConfirmationNotRequiredException_whenUserAccountIsEnabled() {
		String email = "email@email.com";
		User confirmationNotRequiredUser = User.builder().enabled(true).build();
		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationNotRequiredUser);

		assertThrows(AccountConfirmationNotRequiredException.class,
				() -> tokenAuthenticationService.regenerateConfirmationCode(email));
	}

	@Test
	public void regenerateConfirmationCode_shouldPublishRegenerationEvent_whenUserAccountIsNotEnabled() {
		String email = "email@email.com";

		ConfirmacaoCadastro confirmacaoCadastro = ConfirmacaoCadastro.builder().build();
		User confirmationRequiredUser = User.builder().confirmacaoCadastro(confirmacaoCadastro)
				.enabled(false).build();

		when(userDetailsManager.loadUserByUsername(any())).thenReturn(confirmationRequiredUser);
		when(confirmacaoCadastroService.generateCodigoConfirmacacao()).thenReturn("123456");

		assertDoesNotThrow(() -> tokenAuthenticationService.regenerateConfirmationCode(email));
		verify(confirmacaoCadastroService, times(1)).save(confirmacaoCadastro);
		verify(applicationEventPublisher, times(1)).publishEvent(any(RenovacaoCodigoConfirmacaoEvent.class));
	}
}
