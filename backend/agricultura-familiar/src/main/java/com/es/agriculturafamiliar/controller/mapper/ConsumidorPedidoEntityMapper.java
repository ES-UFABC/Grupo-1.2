package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;
import com.es.agriculturafamiliar.entity.consumidorPedido.ConsumidorPedidoEntity;
import com.es.agriculturafamiliar.models.consumidorPedido.ConsumidorPedidoDomain;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumidorPedidoEntityMapper {
    ConsumidorPedidoEntity toEntity(ConsumidorPedidoDomain domainIn);
    CadastroConsumidorDomain toModel(CadastroConsumidorEntity entityIn);
}
