package com.es.agriculturafamiliar.dto.cadastroconsumidor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CadastroConsumidorDtoOut {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;


}
