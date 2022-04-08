package com.es.agriculturafamiliar.dto;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.es.agriculturafamiliar.enums.TipoProdutor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutorRequestDTO {
    @NotNull
    @Valid    
    private UserCredentialsDTO userCredentials;

    @NotBlank
    private String cpfOuCnpj;

    @NotBlank
    private String nome;

    private String nomeFantasia;

    @NotBlank
    private String email;
    
    private List<@Valid EnderecoProdutorRequest> enderecos;
    private String regiaoDeProducao;
    private Boolean atendeNoEnderecoDeProducao;
    private Boolean cadastroEntidade;

    @NotNull
    @Builder.Default
    private Integer tipoProdutor = TipoProdutor.INDIVIDUAL.getCod();
    private Boolean registroOuCertificacao;
    private Boolean agroecologico;
    private Boolean certificacaoAgroecologico;
    private String organico;
    private String geolocalizacao;    
    private Set<@Valid TipoProducaoDTO> tiposProducao;
    private Set<String> telefones;
    private Set<String> entidadesAtendidas;
    private Set<String> formasPagamento;
    private Set<String> registrosOuCertificacoes;
    private Set<String> paginasExternas;

}
