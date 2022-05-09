package com.es.agriculturafamiliar.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequest {

    @NotBlank(message = "Deve-se informar se o endereço é o principal")
    @Pattern(regexp = "(S|N)", message = "o endereço principal só pode receber os valores S ou N")
    private String flagEnderecoPrincipal;

    @NotBlank(message = "CEP deve ser preenchido")
    private String cep;

    @NotBlank(message = "Número deve ser preenchido")
    private String numero;

    private String complemento;

    private String rua;

    private String bairro;

    private String municipio;
}
