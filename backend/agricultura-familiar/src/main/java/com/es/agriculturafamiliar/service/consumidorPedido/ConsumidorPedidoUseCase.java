package com.es.agriculturafamiliar.service.consumidorPedido;

import com.es.agriculturafamiliar.models.consumidorPedido.ConsumidorPedidoDomain;
import com.es.agriculturafamiliar.repository.consumidorPedido.ConsumidorPedidoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ConsumidorPedidoUseCase {

    public static ConsumidorPedidoRepository repository;

    public void cadastraPedido(ConsumidorPedidoDomain DomainIn) {
        repository.salvar(DomainIn);
    }
}
