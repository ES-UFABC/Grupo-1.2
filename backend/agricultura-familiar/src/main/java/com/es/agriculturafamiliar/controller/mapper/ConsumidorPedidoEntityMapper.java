package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.dto.consumidorPedidos.ConsumidorPedidoDTO;
import com.es.agriculturafamiliar.entity.consumidorPedido.ConsumidorPedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumidorPedidoEntityMapper {

    ConsumidorPedidoEntity toEntity(ConsumidorPedidoDTO dto);

}
