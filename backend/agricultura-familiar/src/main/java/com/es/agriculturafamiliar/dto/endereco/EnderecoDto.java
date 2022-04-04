package com.es.agriculturafamiliar.dto.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDto {

    @NonNull
    @Pattern(regexp = "(S|N)", message = "o endereço principal só pode receber os valores S ou N")
    private String flagEnderecoPrincipal;

    @NonNull
    @Positive(message = "CEP não deve conter números negativos")
    @Pattern(regexp = "^[0-9]+", message = "CEP deve ser numérico")
    private String cep;

    @NonNull
    @Positive(message = "Número da residência não deve conter números negativos")
    @Pattern(regexp = "^[0-9]+", message = "Número da residência deve ser numérico")
    private String numero;

    private String complemento;

    private String rua;

    private String bairro;

    private String municipio;
}
