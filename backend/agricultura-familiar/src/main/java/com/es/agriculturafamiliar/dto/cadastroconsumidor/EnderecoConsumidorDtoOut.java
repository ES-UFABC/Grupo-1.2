package com.es.agriculturafamiliar.dto.cadastroconsumidor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoConsumidorDtoOut {
    private Integer idEndereco;
    private String flagEnderecoPrincipal;
    private String cep;
    private String numero;
    private String complemento;
    private String rua;
    private String bairro;
    private String municipio;
    
}
