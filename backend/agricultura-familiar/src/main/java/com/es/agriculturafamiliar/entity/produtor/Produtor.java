package com.es.agriculturafamiliar.entity.produtor;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Produtor {

    @Id
    private String cpfOuCnpj; //considerar id
    private String nome;
    private String nomeFantasia;
    private String email;

    @OneToMany(mappedBy = "produtor")
    private List<Endereco> enderecos = new ArrayList<>();

    private String regiaoDeProducao; //geolocalização?

    private Boolean atendeNoEnderecoDeProducao;
    private Boolean cadastroEntidade;

    private Integer tipoProdutor;

    private Boolean registroOuCertificacao;
    private Boolean agroecologico;
    private Boolean certificacaoAgroecologico;
    private String organico;
    private String geolocalizacao;  //provisorio

    @ManyToMany
    @JoinTable(name="PRODUTOR_TIPO_PRODUCAO",
                joinColumns = @JoinColumn(name="produtor_id"),
                inverseJoinColumns = @JoinColumn(name="tipo_producao_id"))
    private Set<TipoProducao> tiposProducao = new HashSet<>();

    @ElementCollection //Mapeando uma entidade fraca como tabela. Não precisa de classe
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ENTIDADE_ATENDIDA")
    private Set<String> entidadesAtendidas = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "FORMA_PAGAMENTO")
    private Set<String> formasPagamento = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "REGISTROS_CERTIFICADOS")
    private Set<String> registrosOuCertificacoes = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "PAGINAS_EXTERNAS")
    private Set<String> paginasExternas = new HashSet<>();

    public Produtor(String cpfOuCnpj, String nome, String nomeFantasia,
                    String email, String regiaoDeProducao,
                    Boolean atendeNoEnderecoDeProducao,
                    Boolean cadastroEntidade, TipoProdutor tipoProdutor, Boolean registroOuCertificacao,
                    Boolean agroecologico, Boolean certificacaoAgroecologico,
                    String organico, String geolocalizacao) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.regiaoDeProducao = regiaoDeProducao;
        this.atendeNoEnderecoDeProducao = atendeNoEnderecoDeProducao;
        this.cadastroEntidade = cadastroEntidade;
        this.tipoProdutor = tipoProdutor.getCod();
        this.registroOuCertificacao = registroOuCertificacao;
        this.agroecologico = agroecologico;
        this.certificacaoAgroecologico = certificacaoAgroecologico;
        this.organico = organico;
        this.geolocalizacao = geolocalizacao;
    }

    public void setTipoProdutor(TipoProdutor tipo) {this.tipoProdutor = tipo.getCod();}
    public TipoProdutor getTipoProdutor() {return TipoProdutor.toEnum(this.tipoProdutor);}
}
