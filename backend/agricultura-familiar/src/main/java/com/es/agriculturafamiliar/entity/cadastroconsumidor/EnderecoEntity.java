package com.es.agriculturafamiliar.entity.cadastroconsumidor;

import lombok.AllArgsConstructor;

import javax.persistence.Id;

@AllArgsConstructor
public class EnderecoEntity {
    @Id
    private Integer id_endereco;
    private String flagEnderecoPrincipal;
    private String cep;
    private String numero;
    private String complemento;
    private String rua;
    private String bairro;
    private String municipio;
}
