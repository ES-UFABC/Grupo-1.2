package com.es.agriculturafamiliar.dto.response;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.entity.produtor.TipoProducao;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutorPerfilResponse {

    private Long id;
    private String nomeFantasia;
    private List<Endereco> enderecos;
    private String regiaoDeProducao;
    private Boolean atendeNoEnderecoDeProducao;
    private Boolean cadastroEntidade;
    private TipoProdutor tipoProdutor;
    private Boolean registroOuCertificacao;
    private Boolean agroecologico;
    private Boolean certificacaoAgroecologico;
    private String organico;
    private String geolocalizacao;
    private List<Produto> produtos;
    private Set<TipoProducao> tiposProducao;
    private Set<String> telefones;
    private Set<String> entidadesAtendidas;
    private Set<String> formasPagamento;
    private Set<String> registrosOuCertificacoes;
    private Set<String> paginasExternas;
}
