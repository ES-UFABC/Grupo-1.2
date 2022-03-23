package com.es.agriculturafamiliar.produtor.service;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import com.es.agriculturafamiliar.services.ProdutorService;
import com.es.agriculturafamiliar.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        produtor.setRegiaoDeProducao("região");
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
        returnedSavedProdutor.setRegiaoDeProducao("região");
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

    @Test
    public void searchProdutorByCpfOuCnpj_shouldThrowsObjectNotFoundException_whenResourceIsNotFound(){

        when(produtorRepository.findById(any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> produtorService.findProdutorByCpfOuCnpj("43292043742"));
    }

    @Test
    public void findProdutorByCpfOuCnpj_shouldReturnSavedProdutor_whenSuccessful(){
        Produtor produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Prod Teste");
        produtor.setNomeFantasia("Prod Teste");
        produtor.setEmail("prodteste@email.com");;
        produtor.setRegiaoDeProducao("região");
        produtor.setAtendeNoEnderecoDeProducao(true);
        produtor.setCadastroEntidade(true);
        produtor.setTipoProdutor(TipoProdutor.COLETIVO);
        produtor.setRegistroOuCertificacao(false);
        produtor.setAgroecologico(true);
        produtor.setCertificacaoAgroecologico(true);
        produtor.setOrganico("SIM");
        produtor.setGeolocalizacao("");
        produtor.getTelefones().addAll(Arrays.asList("1234512", "1574869"));

        when(produtorRepository.findById(any(String.class))).thenReturn(Optional.of(produtor));

        var returnedProdutor = produtorService.findProdutorByCpfOuCnpj("43292043742");

        assertEquals(produtor.getNome(), returnedProdutor.getNome());
        assertEquals(produtor.getNomeFantasia(), returnedProdutor.getNomeFantasia());
        assertEquals(produtor.getCpfOuCnpj(), returnedProdutor.getCpfOuCnpj());
        assertEquals(produtor.getTelefones(), returnedProdutor.getTelefones());
    }

    @Test
    public void deleteProdutorByCpfOuCnpj_shouldThrowObjectNotFoundException_whenResourceIsNotFound(){
        when(produtorRepository.findById(any(String.class)))
                .thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> produtorService.deleteProdutorByCpfOuCnpj("1"));
    }

    @Test
    public void updateProdutor_shouldThrowObjectNotFoundException_whenResourceIsNotFound() {
        Produtor produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Atualizado");

        assertThrows(ObjectNotFoundException.class, () -> produtorService.updateProdutor(produtor, "43292043742"));
    }

    @Test
    public void updateProdutor_shouldReturnSavedProdutor_whenSuccessful() {
        Produtor produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Prod Teste");
        produtor.setNomeFantasia("Prod Teste");
        produtor.setEmail("prodteste@email.com");;
        produtor.setRegiaoDeProducao("região");
        produtor.setAtendeNoEnderecoDeProducao(true);
        produtor.setCadastroEntidade(true);
        produtor.setTipoProdutor(TipoProdutor.COLETIVO);
        produtor.setRegistroOuCertificacao(false);
        produtor.setAgroecologico(true);
        produtor.setCertificacaoAgroecologico(true);
        produtor.setOrganico("SIM");
        produtor.setGeolocalizacao("");
        produtor.getTelefones().addAll(Arrays.asList("1234512", "1574869"));

        Produtor produtorAtualizado = new Produtor();
        produtorAtualizado.setNome("ATUALIZADO");
        produtorAtualizado.setNomeFantasia("ATUALIZADO");
        produtorAtualizado.setEmail("prodteste@email.com");;
        produtorAtualizado.setRegiaoDeProducao("região");
        produtorAtualizado.setAtendeNoEnderecoDeProducao(true);
        produtorAtualizado.setCadastroEntidade(true);
        produtorAtualizado.setTipoProdutor(TipoProdutor.COLETIVO);
        produtorAtualizado.setRegistroOuCertificacao(false);
        produtorAtualizado.setAgroecologico(true);
        produtorAtualizado.setCertificacaoAgroecologico(true);
        produtorAtualizado.setOrganico("SIM");
        produtorAtualizado.setGeolocalizacao("");
        produtorAtualizado.getTelefones().addAll(Arrays.asList("1234512", "1574869"));

        when(produtorRepository.findById(any(String.class)))
                .thenReturn(Optional.of(produtor));

        when(produtorRepository.save(any(Produtor.class)))
                .thenReturn(produtorAtualizado);

        produtorService.saveProdutor(produtor);
        Produtor returnedProdutor = produtorService.updateProdutor(produtorAtualizado, produtor.getCpfOuCnpj());

        assertNotNull(returnedProdutor.getNome());
        assertNotEquals(produtor.getNomeFantasia(), returnedProdutor.getNomeFantasia());
        assertNotEquals(produtor.getNome(), returnedProdutor.getNome());
        assertEquals(produtor.getRegiaoDeProducao(), returnedProdutor.getRegiaoDeProducao());
        assertEquals(produtor.getCpfOuCnpj(), returnedProdutor.getCpfOuCnpj());
    }
}
