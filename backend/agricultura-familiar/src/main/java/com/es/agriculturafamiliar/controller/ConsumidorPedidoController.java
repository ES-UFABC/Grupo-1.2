package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.dto.consumidorPedidos.ConsumidorPedidoDTO;
import com.es.agriculturafamiliar.service.consumidorPedido.ConsumidorPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/consumidor/pedidos")
public class ConsumidorPedidoController {

    private final ConsumidorPedidoService useCase;

    @PostMapping
    public ResponseEntity<?> cadastraPedido (
            @Valid @RequestBody ConsumidorPedidoDTO requestDTO) {
        log.info("ConsumidorPedidoController - cadastraPedido - idConsumidor: {}, idProdutor {}, pedido: {}",
                requestDTO.getIdConsumidor(), requestDTO.getIdProdutor(), requestDTO.getPedido());

        useCase.cadastraPedido(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deletaPedido (@RequestParam String idConsumidor,
                                                    String idProdutor,
                                                    String pedido) {
        log.info("ConsumidorPedidoController - deletaPedido - idConsumidor: {}, idProdutor {}, pedido: {}",
                idConsumidor, idProdutor, pedido);

        if(idConsumidor.isBlank() || idProdutor.isBlank() || pedido.isBlank()) {
            throw new NoSuchElementException("Parâmetros idConsumidor, idProdutor e pedido são obrigatórios");
        }
        useCase.deletaPedido(idConsumidor,idProdutor,pedido);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
