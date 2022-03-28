package com.es.agriculturafamiliar.dto.cadastroconsumidor;

import com.es.agriculturafamiliar.dto.endereco.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonTypeName(value = "data")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CadastroConsumidorDtoIn {

    @NonNull
    private String nome;

    @NonNull
    private String email;

    @NonNull
    @Positive(message = "telefone não deve conter números negativos")
    @Pattern(regexp = "^[0-9]+", message = "telefone deve ser numérico")
    private String telefone;

    @NonNull
    @Positive(message = "cpf não deve conter números negativos")
    @Pattern(regexp = "^[0-9]+", message = "cpf deve ser numérico")
    private String cpf;

    @NonNull
    private List<EnderecoDto> endereco;
}
