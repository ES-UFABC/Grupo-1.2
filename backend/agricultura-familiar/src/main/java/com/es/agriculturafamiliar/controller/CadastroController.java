package com.es.agriculturafamiliar.controller;


import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorMapper;
import com.es.agriculturafamiliar.dto.request.CadastroConsumidorRequest;
import com.es.agriculturafamiliar.dto.response.CadastroConsumidorResponse;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import com.es.agriculturafamiliar.service.ProdutorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController()
@RequestMapping("/cadastro")
@AllArgsConstructor
public class CadastroController {

    @Autowired
    private final ProdutorService produtorService;

    private final CadastroConsumidorMapper mapper;
    private final CadastroConsumidorUseCase useCase;
    private final ModelMapper modelMapper;

    @PostMapping("/produtor")
    public ResponseEntity<Produtor> saveProdutor(@Valid @RequestBody Produtor produtor){
        Produtor savedProdutor = produtorService.saveProdutor(produtor, produtor.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProdutor);
    }

    @PostMapping("/consumidor")
    public ResponseEntity<CadastroConsumidorResponse> cadastraConsumidor(@Valid @RequestBody CadastroConsumidorRequest requestDTO) {

        final Optional<CadastroConsumidorDomain> usecaseDomainOut = useCase.cadastraConsumidor(mapper.toModel(requestDTO));

        if (usecaseDomainOut.isEmpty()) {
            throw new NoSuchElementException();
        }

        final CadastroConsumidorResponse resultado = mapper.toDto(usecaseDomainOut.get());

        log.info(resultado.toString());


        return ResponseEntity.created(URI.create("/" + resultado.getCpf())).body(resultado);
    }
}
