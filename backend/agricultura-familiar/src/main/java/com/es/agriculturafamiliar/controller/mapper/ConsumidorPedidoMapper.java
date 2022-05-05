package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.dto.consumidorPedidos.ConsumidorPedidoDTO;
import com.es.agriculturafamiliar.models.consumidorPedido.ConsumidorPedidoDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumidorPedidoMapper {

    ConsumidorPedidoDomain toModel(ConsumidorPedidoDTO requestDTO);

}
