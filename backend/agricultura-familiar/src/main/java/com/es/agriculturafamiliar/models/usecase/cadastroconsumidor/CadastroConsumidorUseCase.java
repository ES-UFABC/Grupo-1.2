package com.es.agriculturafamiliar.models.usecase.cadastroconsumidor;

import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;


@Component
@Slf4j
@AllArgsConstructor
public class CadastroConsumidorUseCase {

    private final CadastroConsumidorRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<CadastroConsumidorDomain> cadastraConsumidor(CadastroConsumidorDomain domainIn) {
        log.info("cadastraConsumidor CPF: {}", domainIn.getCpf());

        var verificaExistenciaConsumidor = repository.consultaPorChave(domainIn.getCpf());

        if (verificaExistenciaConsumidor.isPresent()) {
            throw new IllegalArgumentException("O CPF informado já se encontra cadastrado");
        }

        Optional<CadastroConsumidorDomain> resultadoCadastroOptional = repository.salvar(domainIn);
        
        EmailCadastroEvent emailEvent = EmailCadastroEvent.builder()
            .toEmail(domainIn.getEmail()).name(domainIn.getNome()).build();

        applicationEventPublisher.publishEvent(emailEvent);

        return resultadoCadastroOptional;
    }

    public Optional<CadastroConsumidorDomain> consultaConsumidor(String idCPF) {
        log.info("consultaConsumidor CPF: {}", idCPF);

        return repository.consultaPorChave(idCPF);
    }

    public Optional<CadastroConsumidorDomain> atualizaConsumidor(CadastroConsumidorDomain domainIn) {
        log.info("atualizaConsumidor CPF: {}", domainIn.getCpf());

        var verificaExistenciaConsumidor = consultaConsumidor(domainIn.getCpf());

        if (verificaExistenciaConsumidor.isEmpty()) {
            throw new NoSuchElementException("O CPF informado não está cadastrado");
        }

        return repository.salvar(domainIn);
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
