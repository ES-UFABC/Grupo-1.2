package com.es.agriculturafamiliar.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Endereco {

    private Integer id;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String municipio;
}
