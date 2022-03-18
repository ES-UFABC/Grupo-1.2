package com.es.agriculturafamiliar.entity;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String municipio;

    @OneToOne
    @JoinColumn(name = "produtor_id")
    private Produtor produtor;
}
