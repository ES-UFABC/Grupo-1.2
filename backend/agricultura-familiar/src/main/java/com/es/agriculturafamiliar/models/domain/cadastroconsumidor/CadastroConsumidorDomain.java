package com.es.agriculturafamiliar.models.domain.cadastroconsumidor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.es.agriculturafamiliar.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastroConsumidorDomain {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private List<EnderecoDomain> endereco;
    private User user;
}
