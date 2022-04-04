package com.es.agriculturafamiliar.entity.produtor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TipoProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @ManyToMany(mappedBy = "tiposProducao")
    private List<Produtor> produtores = new ArrayList<>();

    public TipoProducao(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
