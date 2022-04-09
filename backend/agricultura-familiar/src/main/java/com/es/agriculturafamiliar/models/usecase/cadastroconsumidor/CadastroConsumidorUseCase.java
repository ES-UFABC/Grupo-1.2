package com.es.agriculturafamiliar.models.usecase.cadastroconsumidor;

import com.es.agriculturafamiliar.constants.RoleType;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
import com.es.agriculturafamiliar.service.ICustomUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Component
@Slf4j
@AllArgsConstructor
public class CadastroConsumidorUseCase {

    private final CadastroConsumidorRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ICustomUserDetailsService<User> customUserDetailsService;
    private static final Set<Role> CONSUMIDOR_ROLES =  Set.of(Role.builder().role(RoleType.CONSUMIDOR).build());

    @Transactional
    public Optional<CadastroConsumidorDomain> cadastraConsumidor(CadastroConsumidorDomain domainIn) {
        log.info("cadastraConsumidor CPF: {}", domainIn.getCpf());

        var verificaExistenciaConsumidor = consultaConsumidor(domainIn.getCpf());

        if (verificaExistenciaConsumidor.isPresent()) {
            throw new IllegalArgumentException("O CPF informado já se encontra cadastrado");
        }

        User user = domainIn.getUser();
        user.setRoles(CONSUMIDOR_ROLES);
        User savedUSER = customUserDetailsService.createUser(user);
        
        domainIn.setUser(savedUSER);

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

    public Optional<CadastroConsumidorDomain> findByUserId(Long id) {
        log.info("Procurando pelo id de usuário: {}", id);
        return repository.consultaPorUserId(id);

    }
}
