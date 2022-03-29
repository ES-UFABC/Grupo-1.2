package com.es.agriculturafamiliar.controller.mapper;

import com.es.agriculturafamiliar.dto.cadastroconsumidor.CadastroConsumidorDtoIn;
import com.es.agriculturafamiliar.models.domain.cadastroconsumidor.CadastroConsumidorDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface CadastroConsumidorMapper {
    @Mapping(target = "endereco.getIdEndereco", source = "endereco")
    CadastroConsumidorDomain toModel(CadastroConsumidorDtoIn requestDTO);
    @Mapping(target = "endereco", source = "endereco")
    CadastroConsumidorDtoIn toDto(CadastroConsumidorDomain domainOut);
}