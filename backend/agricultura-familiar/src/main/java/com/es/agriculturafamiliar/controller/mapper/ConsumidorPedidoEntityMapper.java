package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.dto.request.ConsumidorPedidoRequest;
import com.es.agriculturafamiliar.entity.consumidorPedido.ConsumidorPedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumidorPedidoEntityMapper {

    ConsumidorPedidoEntity toEntity(ConsumidorPedidoRequest dto);

}
