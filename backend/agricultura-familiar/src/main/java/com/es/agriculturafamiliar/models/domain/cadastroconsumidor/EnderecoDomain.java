package com.es.agriculturafamiliar.models.domain.cadastroconsumidor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDomain {
    private Integer idEndereco;
    private String flagEnderecoPrincipal;
    private String cep;
    private String numero;
    private String complemento;
    private String rua;
    private String bairro;
    private String municipio;
}
