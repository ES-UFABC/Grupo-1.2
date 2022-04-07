package com.es.agriculturafamiliar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.service.ProdutorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProdutorController.class)
public class ProdutorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProdutorService produtorService;
    @MockBean
    EnderecoRepository enderecoRepository;

    @Autowired
    private ProdutorController produtorController;
    private Produtor produtor;
    private static ObjectMapper objectMapper;

    public static final String BASE_ENDPOINT = "/cadastro/produtor";

    @BeforeEach
    public void setup(){
        produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Prod Teste");
        produtor.setNomeFantasia("Prod Teste");
        produtor.setTipoProdutor(TipoProdutor.COLETIVO);
        produtor.setOrganico("SIM");

        objectMapper = new ObjectMapper();
    }


    @Test
    void findById_shouldReturnStatusOk_whenProdutorExists() throws Exception {

        when(produtorService.saveProdutor(any(Produtor.class), any())).thenReturn(produtor);
        produtorController.saveProdutor(produtor);
        System.out.println(produtor.getId());
        mockMvc.perform(get(BASE_ENDPOINT +"/{id}", produtor.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void saveProdutor_shouldReturnStatusCreated_whenValidRequestBodyIsReceived() throws Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setCpfOuCnpj("123456789");
        produtorTeste.setNome("teste");
        produtorTeste.setEmail("emailteste@email.com");

        Assertions.assertEquals(produtorController.saveProdutor(produtorTeste).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void saveProdutor_shouldReturnBadRequest_WhenMandatoryFieldIsMissing() throws  Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setCpfOuCnpj("");

        mockMvc.perform(post(BASE_ENDPOINT)
                        .content(objectMapper.writeValueAsString(produtorTeste))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProdutorById_shouldReturnNoContent_whenProdutorExists(){
        Assertions.assertEquals(produtorController.deleteProdutorById(produtor.getId()).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void updateEndereco_shouldReturnNoContent_whenUpdateIsSuccessful() throws Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setNome("teste");
        produtorTeste.setEmail("email");

        Assertions.assertEquals(produtorController.updateProdutor(produtorTeste, 1L).getStatusCode(), HttpStatus.OK);

    }

    @Test
    void findAll_shouldReturnOk_whenProdutorAreRequested() throws Exception {
        mockMvc.perform(get(BASE_ENDPOINT))
                .andExpect(status().isOk());
    }

}
