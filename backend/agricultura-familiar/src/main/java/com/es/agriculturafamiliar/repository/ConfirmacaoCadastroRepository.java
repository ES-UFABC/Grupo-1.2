package com.es.agriculturafamiliar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;

public interface ConfirmacaoCadastroRepository extends JpaRepository<ConfirmacaoCadastro, Long> {
	Optional<ConfirmacaoCadastro> findByUserId(Long userId);

}
