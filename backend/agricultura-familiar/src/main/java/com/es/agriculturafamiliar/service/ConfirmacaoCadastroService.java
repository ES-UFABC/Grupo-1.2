package com.es.agriculturafamiliar.service;

import org.springframework.stereotype.Service;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.repository.ConfirmacaoCadastroRepository;
import com.es.agriculturafamiliar.util.ITokenGeneratorStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmacaoCadastroService {
	private final ConfirmacaoCadastroRepository confirmacaoCadastroRepository;
	private final ITokenGeneratorStrategy<String> tokenGeneratorStrategy;
	
	public void validateToken(Long userId, String token) {
		
		
	}
	
	public ConfirmacaoCadastro createConfirmacaoCadastro() {		
		String token = tokenGeneratorStrategy.generateToken();
		return ConfirmacaoCadastro.builder()
				.codigo(token)
				.build();
	}
}
