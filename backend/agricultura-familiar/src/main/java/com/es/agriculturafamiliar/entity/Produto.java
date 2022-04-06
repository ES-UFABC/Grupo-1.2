package com.es.agriculturafamiliar.entity;

import com.es.agriculturafamiliar.enums.Tipologia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String nome;

    @NotEmpty
    private String unidadeDeMedida;

    @NotNull
    private Integer tipologia;

    private Long estimavaDeProducaoSazonalidade;

    private String foto;

    @NotNull
    private Double preco;

    private Boolean condicoesEspeciaisDeEntrega = false;

    public Produto(Long id, String nome, String unidadeDeMedida, Tipologia tipologia, Long estimavaDeProducaoSazonalidade, String foto, Double preco, Boolean condicoesEspeciaisDeEntrega) {
        this.id = id;
        this.nome = nome;
        this.unidadeDeMedida = unidadeDeMedida;
        this.tipologia = tipologia.getCod();
        this.estimavaDeProducaoSazonalidade = estimavaDeProducaoSazonalidade;
        this.foto = foto;
        this.preco = preco;
        this.condicoesEspeciaisDeEntrega = condicoesEspeciaisDeEntrega;
    }

    public void setTipologia(Tipologia tipologia) {this.tipologia = tipologia.getCod();}
    public Tipologia getTipologia() {return Tipologia.toEnum(this.tipologia);}
}
