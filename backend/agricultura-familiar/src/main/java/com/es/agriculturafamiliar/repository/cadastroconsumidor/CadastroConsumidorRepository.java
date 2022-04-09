package com.es.agriculturafamiliar.repository.cadastroconsumidor;

import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorEntityMapper;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.jpa.CadastroConsumidorRepositoryCrud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CadastroConsumidorRepository {

    private final CadastroConsumidorEntityMapper mapper;
    private final CadastroConsumidorRepositoryCrud cadastroConsumidorRepositoryCrud;

    public Optional<CadastroConsumidorDomain> salvar(CadastroConsumidorDomain useCaseDomainIn) {
        var salvaCadastroConsumidor = cadastroConsumidorRepositoryCrud.save(mapper.toEntity(useCaseDomainIn));
        log.info("Consumidor CPF {} cadastrado com sucesso", useCaseDomainIn.getCpf());
        return Optional.of(mapper.toModel(salvaCadastroConsumidor));
    }

    public Optional<CadastroConsumidorDomain> consultaPorChave(String idCPF) {
        var consultaCadastroConsumidor = cadastroConsumidorRepositoryCrud.findById(idCPF);
        log.info("Consumidor CPF {} consultado com sucesso", idCPF);

        if (consultaCadastroConsumidor.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(mapper.toModel(consultaCadastroConsumidor.get()));
    }

    public void delete(String idCPF) {
        cadastroConsumidorRepositoryCrud.deleteById(idCPF);
        log.info("Consumidor CPF {} deletado com sucesso", idCPF);
    }

    public Optional<CadastroConsumidorDomain> consultaPorUserId(Long id) {
        var consultaCadastroConsumidor = cadastroConsumidorRepositoryCrud.findCadastroConsumidorEntityByUserId(id);

        if (consultaCadastroConsumidor.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(mapper.toModel(consultaCadastroConsumidor.get()));
    }
}
