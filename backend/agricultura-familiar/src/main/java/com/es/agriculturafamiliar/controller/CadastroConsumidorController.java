package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorMapper;
import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoIn;
import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoOut;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    @PostMapping
    public ResponseEntity<CadastroConsumidorDtoOut> cadastraConsumidor(@Valid @RequestBody CadastroConsumidorDtoIn requestDTO) {

        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.cadastraConsumidor(mapper.toModel(requestDTO));

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorDtoOut resultado = mapper.toDto(usecaseDomainOut.get());

        log.info(resultado.toString());
        

        return ResponseEntity.created(URI.create("/" + resultado.getCpf())).body(resultado);
    }

    @PutMapping("/{IdCPF}")
    public ResponseEntity<CadastroConsumidorDtoOut> atualizaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF,
            @Valid @RequestBody CadastroConsumidorDtoIn requestDTO
            ) {
        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.atualizaConsumidor(mapper.toModel(requestDTO));

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorDtoOut resultado = mapper.toDto(usecaseDomainOut.get());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{IdCPF}")
    public ResponseEntity<CadastroConsumidorDtoOut> consultaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {
        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.consultaConsumidor(idCPF);

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorDtoOut resultado = mapper.toDto(usecaseDomainOut.get());

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{IdCPF}")
    public ResponseEntity<Object> deletaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {

        useCase.deletaConsumidor(idCPF);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
