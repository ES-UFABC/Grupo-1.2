package com.es.agriculturafamiliar.dto.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDto {

    @NotBlank(message = "Deve-se informar se o endereço é o principal")
    @Pattern(regexp = "(S|N)", message = "o endereço principal só pode receber os valores S ou N")
    @JsonProperty(value = "flag_endereco_principal")
    private String flagEnderecoPrincipal;

    @NotBlank(message = "CEP deve ser preenchido")
    @JsonProperty(value = "CEP")
    private String cep;

    @NotBlank(message = "Número deve ser preenchido")
    @JsonProperty(value = "numero")
    private String numero;

    private String complemento;

    private String rua;

    private String bairro;

    private String municipio;
}
