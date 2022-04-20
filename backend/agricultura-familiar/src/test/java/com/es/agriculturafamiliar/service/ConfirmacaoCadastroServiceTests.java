package com.es.agriculturafamiliar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.repository.ConfirmacaoCadastroRepository;
import com.es.agriculturafamiliar.util.ITokenGeneratorStrategy;

@ExtendWith(MockitoExtension.class)
public class ConfirmacaoCadastroServiceTests {

	@InjectMocks
	private ConfirmacaoCadastroService confirmacaoCadastroService;

	@Mock
	private ConfirmacaoCadastroRepository confirmacaoCadastroRepository;

	@Mock
	private ITokenGeneratorStrategy<String> tokenGeneratorStrategy;

	@Test
	public void createConfirmacaoCadastro_shouldReturnedCodigoConfirmacaoInstance_whenCalled() {
		final String expectedToken = "123456";
		when(tokenGeneratorStrategy.generateToken()).thenReturn(expectedToken);

		ConfirmacaoCadastro createConfirmacaoCadastro = confirmacaoCadastroService.createConfirmacaoCadastro();
		String actualToken = createConfirmacaoCadastro.getCodigo();

		assertEquals(expectedToken, actualToken);

	}

	@Test
	public void generateCodigoConfirmacacao_shouldCallTokenGeneratorStrategyAndReturnToken_whenCalled() {
		final String expectedToken = "123456";
		when(tokenGeneratorStrategy.generateToken()).thenReturn(expectedToken);

		String actualToken = confirmacaoCadastroService.generateCodigoConfirmacacao();
		verify(tokenGeneratorStrategy, times(1)).generateToken();
		assertEquals(expectedToken, actualToken);
	}

	@Test
	public void save_shouldCallRepository_whenProperlyCalled() {
		ConfirmacaoCadastro confirmacaoCadastro = ConfirmacaoCadastro.builder().codigo("123456").build();
		
		confirmacaoCadastroService.save(confirmacaoCadastro);

		verify(confirmacaoCadastroRepository, times(1)).save(confirmacaoCadastro);
	}
}
