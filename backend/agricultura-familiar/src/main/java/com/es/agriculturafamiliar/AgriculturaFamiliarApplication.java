package com.es.agriculturafamiliar;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoEndereco;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.services.ProdutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import java.util.Arrays;

@EnableAsync
@EnableRetry
@SpringBootApplication
public class AgriculturaFamiliarApplication implements CommandLineRunner {

	@Autowired
	private ProdutorService produtorService;

	@Autowired
	private EnderecoRepository enderecoService;

	public static void main(String[] args) {
		SpringApplication.run(AgriculturaFamiliarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Endereco endereco = new Endereco(null, "rua", "112", "bairro", "cep", "municipio", TipoEndereco.PRODUCAO, null);
		Endereco endereco2 = new Endereco(null, "rua", "112", "bairro", "cep", "municipio", TipoEndereco.COMERCIALIZACAO, null);

		Produtor prod = new Produtor();
		prod.setCpfOuCnpj("43292043742");
		prod.setNome("Prod Teste");
		prod.setNomeFantasia("Prod Teste");
		prod.setEmail("prodteste@email.com");;
		prod.setRegiaoDeProducao("região");
		prod.setAtendeNoEnderecoDeProducao(true);
		prod.setCadastroEntidade(true);
		prod.setTipoProdutor(TipoProdutor.COLETIVO);
		prod.setRegistroOuCertificacao(false);
		prod.setAgroecologico(true);
		prod.setCertificacaoAgroecologico(true);
		prod.setOrganico("SIM");
		prod.setGeolocalizacao("");
		prod.getTelefones().addAll(Arrays.asList("1234512", "1574869"));
		prod.getEnderecos().addAll(Arrays.asList(endereco, endereco2));
		endereco.setProdutor(prod);
		endereco2.setProdutor(prod);

		prod = produtorService.saveProdutor(prod);
		enderecoService.saveAll(Arrays.asList(endereco, endereco2));

	}
}
