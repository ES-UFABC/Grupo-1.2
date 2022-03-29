package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CadastroConsumidorEntityMapper {
    @Mapping(target = "endereco", source = "endereco")
    CadastroConsumidorEntity toEntity(CadastroConsumidorDomain domainIn);
    @Mapping(target = "endereco", source = "endereco")
    CadastroConsumidorDomain toModel(CadastroConsumidorEntity entityIn);
}
