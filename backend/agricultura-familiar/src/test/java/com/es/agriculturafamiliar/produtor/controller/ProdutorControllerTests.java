package com.es.agriculturafamiliar.produtor.controller;

import com.es.agriculturafamiliar.controller.ProdutorController;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.services.ProdutorService;
import com.es.agriculturafamiliar.services.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    public static final String BASE_ENDPOINT = "/api/v1/produtor";

    @BeforeEach
    public void setup(){
        produtor = new Produtor();
        produtor.setCpfOuCnpj("43292043742");
        produtor.setNome("Prod Teste");
        produtor.setNomeFantasia("Prod Teste");

        objectMapper = new ObjectMapper();
    }


    @Test
    void findByCpfOuCnpj_shouldReturnStatusOk_whenProdutorExists() throws Exception {

        when(produtorService.findProdutorByCpfOuCnpj(any(String.class))).thenReturn(produtor);

        mockMvc.perform(get(BASE_ENDPOINT +"/{cpfOuCnpj}", "43292043742"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", Matchers.is(produtor.getNome())));
    }

    @Test
    void findByCpfOuCnpj_shouldReturnNotFound_whenProdutorDoesNotExists() throws Exception {

        when(produtorService.findProdutorByCpfOuCnpj(any(String.class))).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(get(BASE_ENDPOINT + "/{cpfOuCnpj}", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveProdutor_shouldReturnStatusCreated_whenValidRequestBodyIsReceived() throws Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setCpfOuCnpj("123456789");
        produtorTeste.setNome("teste");

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
    void deleteProdutorByCpfOuCnpj_shouldReturnNoContent_whenProdutorExists(){
        Assertions.assertEquals(produtorController.deleteProdutor(produtor.getCpfOuCnpj()).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void updateEndereco_shouldReturnNoContent_whenUpdateIsSuccessful() throws Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setNome("teste");
        produtorTeste.setEmail("email");

        Assertions.assertEquals(produtorController.updateProdutor(produtorTeste, "43292043742").getStatusCode(), HttpStatus.OK);

    }

    @Test
    void findAll_shouldReturnOk_whenProdutorAreRequested() throws Exception {
        mockMvc.perform(get(BASE_ENDPOINT))
                .andExpect(status().isOk());
    }

}
