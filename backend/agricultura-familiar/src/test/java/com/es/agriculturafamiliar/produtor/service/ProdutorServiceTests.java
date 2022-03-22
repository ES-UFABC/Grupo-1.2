package com.es.agriculturafamiliar.produtor.service;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import com.es.agriculturafamiliar.services.ProdutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutorServiceTests {

    @InjectMocks
    private static ProdutorService produtorService;

    @Mock
    private static ProdutorRepository produtorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void saveProdutor_shouldReturnSavedProdutor_whenSuccessful(){
        Produtor produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Prod Teste");
        produtor.setNomeFantasia("Prod Teste");
        produtor.setEmail("prodteste@email.com");;
        produtor.setEnderecoDeProducao(null);
        produtor.setRegiaoDeProducao("região");
        produtor.setEnderecoDeComercializacao(null);
        produtor.setAtendeNoEnderecoDeProducao(true);
        produtor.setCadastroEntidade(true);
        produtor.setTipoProdutor(TipoProdutor.COLETIVO);
        produtor.setRegistroOuCertificacao(false);
        produtor.setAgroecologico(true);
        produtor.setCertificacaoAgroecologico(true);
        produtor.setOrganico("SIM");
        produtor.setGeolocalizacao("");
        produtor.getTelefones().addAll(Arrays.asList("1234512", "1574869"));

        Produtor returnedSavedProdutor = new Produtor();
        returnedSavedProdutor.setCpfOuCnpj("43292043742");
        returnedSavedProdutor.setNome("Prod Teste");
        returnedSavedProdutor.setNomeFantasia("Prod Teste");
        returnedSavedProdutor.setEmail("prodteste@email.com");;
        returnedSavedProdutor.setEnderecoDeProducao(null);
        returnedSavedProdutor.setRegiaoDeProducao("região");
        returnedSavedProdutor.setEnderecoDeComercializacao(null);
        returnedSavedProdutor.setAtendeNoEnderecoDeProducao(true);
        returnedSavedProdutor.setCadastroEntidade(true);
        returnedSavedProdutor.setTipoProdutor(TipoProdutor.COLETIVO);
        returnedSavedProdutor.setRegistroOuCertificacao(false);
        returnedSavedProdutor.setAgroecologico(true);
        returnedSavedProdutor.setCertificacaoAgroecologico(true);
        returnedSavedProdutor.setOrganico("SIM");
        returnedSavedProdutor.setGeolocalizacao("");
        returnedSavedProdutor.getTelefones().addAll(Arrays.asList("1234512", "1574869"));

        when(produtorRepository.save(any(Produtor.class)))
                .thenReturn(returnedSavedProdutor);

        var savedProdutor = produtorService.saveProdutor(produtor);

        assertEquals(produtor, savedProdutor);
    }
}
