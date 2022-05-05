package com.es.agriculturafamiliar.repository.consumidorPedido.jpa;

import com.es.agriculturafamiliar.entity.consumidorPedido.ConsumidorPedidoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConsumidorPedidoRepository extends CrudRepository<ConsumidorPedidoEntity, Integer> {

    void deleteByIdConsumidorAndIdProdutorAndPedido(String idConsumidor, float idProdutor, String pedido);
}
