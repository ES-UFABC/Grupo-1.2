package com.es.agriculturafamiliar.models.usecase.cadastroconsumidor;

import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;


@Component
@Slf4j
@AllArgsConstructor
public class CadastroConsumidorUseCase {

    private final CadastroConsumidorRepository repository;

    public Optional<CadastroConsumidorDomain> cadastraConsumidor(CadastroConsumidorDomain DomainIn) {
        log.info("cadastraConsumidor CPF: {}", DomainIn.getCpf());

        var verificaExistenciaConsumidor = consultaConsumidor(DomainIn.getCpf());

        if (verificaExistenciaConsumidor.isPresent()) {
            throw new IllegalArgumentException("O CPF informado já se encontra cadastrado");
        }

        return repository.salvar(DomainIn);
    }

    public Optional<CadastroConsumidorDomain> consultaConsumidor(String idCPF) {
        log.info("consultaConsumidor CPF: {}", idCPF);

        return repository.consultaPorChave(idCPF);
    }

    public Optional<CadastroConsumidorDomain> atualizaConsumidor(CadastroConsumidorDomain DomainIn) {
        log.info("atualizaConsumidor CPF: {}", DomainIn.getCpf());

        var verificaExistenciaConsumidor = consultaConsumidor(DomainIn.getCpf());

        if (verificaExistenciaConsumidor.isEmpty()) {
            throw new NoSuchElementException("O CPF informado não está cadastrado");
        }

        return repository.salvar(DomainIn);
    }

    public void deletaConsumidor(String idCPF) {
        log.info("deletaConsumidor CPF: {}", idCPF);

        var verificaExistenciaConsumidor = consultaConsumidor(idCPF);

        if (verificaExistenciaConsumidor.isEmpty()) {
            throw new NoSuchElementException("O CPF informado não está cadastrado");
        }

        repository.delete(idCPF);
    }
}
