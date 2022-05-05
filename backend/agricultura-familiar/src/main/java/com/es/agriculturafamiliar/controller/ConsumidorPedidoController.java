package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.controller.mapper.ConsumidorPedidoMapper;
import com.es.agriculturafamiliar.dto.consumidorPedidos.ConsumidorPedidoDTO;
import com.es.agriculturafamiliar.service.consumidorPedido.ConsumidorPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/consumidor/pedido")
public class ConsumidorPedidoController {

    public static ConsumidorPedidoMapper mapper;
    public static ConsumidorPedidoUseCase useCase;

    @PostMapping
    public ResponseEntity<?> cadastraPedido (
            @Valid @RequestBody ConsumidorPedidoDTO requestDTO) {
        log.info("ConsumidorPedidoController - cadastraPedido - idConsumidor: {}, idProdutor {}, pedido: {}",
                requestDTO.getIdConsumidor(), requestDTO.getIdProdutor(), requestDTO.getPedido());

        useCase.cadastraPedido(mapper.toModel(requestDTO));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
