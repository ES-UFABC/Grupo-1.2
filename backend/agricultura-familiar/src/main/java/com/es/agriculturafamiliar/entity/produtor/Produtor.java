package com.es.agriculturafamiliar.entity.produtor;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import lombok.Data;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Produtor {


    private String cpfOuCnpj; //considerar id
    private String nome;
    private String nomeFantasia;
    private String email;
    private Endereco enderecoDeProducao;
    private String regiaoDeProducao; //geolocalização?
    private Endereco enderecoDeComercializacao;
    private Boolean atendeNoEnderecoDeProducao;
    private Boolean cadastroEntidade;
    private TipoProdutor tipoProdutor;
    private Boolean registroOuCertificacao;
    private Boolean agroecologico;
    private Boolean certificacaoAgroecologico;
    private String organico;
    private String geolocalizacao;  //provisorio

    private Set<String> telefones = new HashSet<>();
    private Set<String> entidadesAtendidas = new HashSet<>();
    private Set<String> formasPagamento = new HashSet<>();
    private Set<String> registrosOuCertificacoes = new HashSet<>();
    private Set<String> paginasExternas = new HashSet<>();
    private Set<TipoProducao> tiposProducao = new HashSet<>();
}
