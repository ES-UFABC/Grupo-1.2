package com.es.agriculturafamiliar.models.domain.cadastroconsumidor;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDomain {
    private Integer id_endereco;
    private String flagEnderecoPrincipal;
    private String cep;
    private String numero;
    private String complemento;
    private String rua;
    private String bairro;
    private String municipio;
}
