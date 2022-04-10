package com.es.agriculturafamiliar.entity.cadastroconsumidor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@AllArgsConstructor
@Data
@Entity
@Table(name = "endereco_consumidor")
@NoArgsConstructor
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id_endereco")
    private Integer idEndereco;
    @Column(name = "flag_endereco_principal")
    private String flagEnderecoPrincipal;
    @Column(name = "cep")
    private String cep;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "municipio")
    private String municipio;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="cpf")
    private CadastroConsumidorEntity consumidor;

    
}
