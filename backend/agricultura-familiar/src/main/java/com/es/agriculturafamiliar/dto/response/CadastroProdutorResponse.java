package com.es.agriculturafamiliar.dto.response;

import com.es.agriculturafamiliar.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroProdutorResponse {

    private String cpfOuCnpj;
    private String nome;
    private String nomeFantasia;
    private List<Endereco> enderecos;
    private Set<String> telefones;
    private UserResponse user;

}
