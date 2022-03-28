package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.controller.basepath.MudaBasePath;
import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorMapper;
import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoIn;
import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoOut;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(maxAge = 3660)
@MudaBasePath
@RequiredArgsConstructor
public class CadastroConsumidorController {

    private final String PATH = "/cadastro_consumidor";
    private final CadastroConsumidorMapper mapper;
    private final CadastroConsumidorUseCase useCase;

    @CrossOrigin("http://localhost:9000")
    @PostMapping(PATH)
    public ResponseEntity<CadastroConsumidorDtoOut> cadastraConsumidor(@Valid @RequestBody CadastroConsumidorDtoIn requestDTO) {

        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.cadastraConsumidor(mapper.toModel(requestDTO));

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorDtoOut resultado = mapper.toDto(usecaseDomainOut.get());

        return ResponseEntity.ok(resultado);
    }

    @PutMapping(PATH + "/{IdCPF}")
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

    @GetMapping(PATH + "/{IdCPF}")
    public ResponseEntity<CadastroConsumidorDtoOut> consultaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {
        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.consultaConsumidor(idCPF);

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorDtoOut resultado = mapper.toDto(usecaseDomainOut.get());

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping(PATH + "/{IdCPF}")
    public ResponseEntity<Object> deletaConsumidor (
            @PathVariable(name = "IdCPF") String idCPF) {

        useCase.deletaConsumidor(idCPF);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
