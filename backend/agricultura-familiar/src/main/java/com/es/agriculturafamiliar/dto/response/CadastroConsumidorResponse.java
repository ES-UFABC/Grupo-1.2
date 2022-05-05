package com.es.agriculturafamiliar.dto.response;

import java.util.List;

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
public class CadastroConsumidorResponse {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private UserResponse user;
    private List<EnderecoConsumidorResponse> endereco;


}
