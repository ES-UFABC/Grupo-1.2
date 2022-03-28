package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoIn;
import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoOut;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CadastroConsumidorMapper {
    CadastroConsumidorDomain toModel(CadastroConsumidorDtoIn requestDTO);
    CadastroConsumidorDtoOut toDto(CadastroConsumidorDomain domainOut);
}