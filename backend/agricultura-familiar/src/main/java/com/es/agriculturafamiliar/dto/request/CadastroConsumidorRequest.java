package com.es.agriculturafamiliar.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class CadastroConsumidorRequest {

    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;

    @NotBlank(message = "Email deve ser preenchido")
    private String email;

    @NotBlank(message = "Telefone deve ser preenchido")
    private String telefone;

    @NotBlank(message = "CPF deve ser preenchido")
    private String cpf;

    @NotNull(message ="Uma lista de endereços deve ser fornecido")
    private List<com.es.agriculturafamiliar.dto.request.EnderecoRequest> endereco;
    
    @NotNull
    private @Valid UserCredentialsRequest user;
}
