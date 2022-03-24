package com.es.agriculturafamiliar.entity;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cep;
    @NotBlank
    private String municipio;
    @NotNull
    private Integer tipoEndereco;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name="produtor_id")
    private Produtor produtor;

    public Endereco(Long id, String rua, String numero, String bairro, String cep, String municipio, TipoEndereco tipoEndereco, Produtor produtor) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.municipio = municipio;
        this.tipoEndereco = tipoEndereco.getCod();
        this.produtor = produtor;
    }

    public void setTipoEndereco(TipoEndereco tipo) {this.tipoEndereco = tipo.getCod();}
    public TipoEndereco getTipoEndereco() {return TipoEndereco.toEnum(this.tipoEndereco);}
}
