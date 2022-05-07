package com.es.agriculturafamiliar.dto.request;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.entity.produtor.TipoProducao;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UpdateProdutorResquest {

    @NotBlank(message = "nome fantasia não pode ser vazio")
    private String nomeFantasia;
    private String regiaoDeProducao;
    private Boolean atendeNoEnderecoDeProducao;
    private Boolean cadastroEntidade;
    @NotNull(message = "tipo de produtor é obrigatório")
    private TipoProdutor tipoProdutor;
    private Boolean registroOuCertificacao;
    private Boolean certificacaoAgroecologico;
    private Boolean agroecologico;
    private String organico;
    private Set<String> entidadesAtendidas;
    private Set<TipoProducao> tiposProducao;
    @NotEmpty(message="ao menos um telefone deve ser informado")
    private Set<String> telefones;
    @NotEmpty(message="ao menos uma forma de pagamento deve ser informada")
    private Set<String> formasPagamento;
    private Set<String> registrosOuCertificacoes;
    private Set<String> paginasExternas;
}
