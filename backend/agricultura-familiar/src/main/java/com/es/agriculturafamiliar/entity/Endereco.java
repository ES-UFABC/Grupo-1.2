package com.es.agriculturafamiliar.entity;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoEndereco;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
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
    private Integer tipoEndereco;

    @OneToOne
    @JoinColumn(name = "produtor_id")
    private Produtor produtor;

    public Endereco(Integer id, String rua, String numero, String bairro, String cep, String municipio, TipoEndereco tipoEndereco, Produtor produtor) {
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
