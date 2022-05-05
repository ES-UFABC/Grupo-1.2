package com.es.agriculturafamiliar.repository.consumidorPedido;

import com.es.agriculturafamiliar.controller.mapper.ConsumidorPedidoEntityMapper;
import com.es.agriculturafamiliar.models.consumidorPedido.ConsumidorPedidoDomain;
import com.es.agriculturafamiliar.repository.consumidorPedido.jpa.ConsumidorPedidoRepositoryCrud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ConsumidorPedidoRepository {

    private final ConsumidorPedidoEntityMapper mapper;
    private final ConsumidorPedidoRepositoryCrud consumidorPedidoRepositoryCrud;

    public void salvar(ConsumidorPedidoDomain useCaseDomainIn) {
        consumidorPedidoRepositoryCrud.save(mapper.toEntity(useCaseDomainIn));
        log.info("Pedido cadastrado com sucesso", useCaseDomainIn);
    }
}
