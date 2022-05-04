package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorMapper;
import com.es.agriculturafamiliar.dto.request.CadastroConsumidorRequest;
import com.es.agriculturafamiliar.dto.response.CadastroConsumidorResponse;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro/consumidor")
public class CadastroConsumidorController {
    
    private final CadastroConsumidorMapper mapper;
    private final CadastroConsumidorUseCase useCase;
    private final ModelMapper modelMapper;


    @PutMapping("/{IdCPF}")
    public ResponseEntity<CadastroConsumidorResponse> atualizaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF,
            @Valid @RequestBody CadastroConsumidorRequest requestDTO
            ) {
        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.atualizaConsumidor(mapper.toModel(requestDTO));

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorResponse resultado = mapper.toDto(usecaseDomainOut.get());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{IdCPF}")
    public ResponseEntity<CadastroConsumidorResponse> consultaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {
        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.consultaConsumidor(idCPF);

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        log.info("usecaseDomainOut antes do mapper: {} - ", usecaseDomainOut);
        final CadastroConsumidorResponse resultado = modelMapper.map(usecaseDomainOut.get(), CadastroConsumidorResponse.class);

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{IdCPF}")
    public ResponseEntity<Object> deletaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {

        useCase.deletaConsumidor(idCPF);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
