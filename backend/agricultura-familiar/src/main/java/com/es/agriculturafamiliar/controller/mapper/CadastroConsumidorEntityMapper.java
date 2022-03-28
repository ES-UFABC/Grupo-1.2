package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CadastroConsumidorEntityMapper {
    CadastroConsumidorEntity toEntity(CadastroConsumidorDomain domainIn);
    CadastroConsumidorDomain toModel(CadastroConsumidorEntity entityIn);
}
