package com.es.agriculturafamiliar.dto.request;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.enums.TipoProdutor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroProdutorRequest {
    @NotNull
    @Valid
    private UserCredentialsRequest user;

    @NotBlank(message = "o cpf/cnpj é obrigatório")
    private String cpfOuCnpj;
    @NotBlank(message = "o nome é obrigatório")
    private String nome;
    @NotBlank(message = "o nome fantasia é obrigatório")
    private String nomeFantasia;
    @NotEmpty(message="ao menos um endereço deve ser informado")
    private List<Endereco> enderecos;
    @NotEmpty(message="ao menos um telefone deve ser informado")
    private Set<String> telefones;
    @NotNull(message = "deve ser informado se o produtor atende no endereço de produção")
    private Boolean atendeNoEnderecoDeProducao;

}
