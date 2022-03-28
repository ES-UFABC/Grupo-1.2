package com.es.agriculturafamiliar.entity.cadastroconsumidor;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
public class CadastroConsumidorEntity {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    @ManyToOne
    private List<EnderecoEntity> endereco;

}
