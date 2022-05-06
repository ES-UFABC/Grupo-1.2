package com.es.agriculturafamiliar.entity;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.Tipologia;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String nome;
    @NotEmpty
    private String unidadeDeMedida;
    @NotNull
    private Integer tipologia;
    private Long estimavaDeProducaoSazonalidade;
    private String foto;
    @NotNull
    private Double preco;
    @NotNull
    private Double quantidade;
    private Boolean condicoesEspeciaisDeEntrega = false;

    @ManyToOne
    @JoinColumn(name = "produtor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Produtor produtor;


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
