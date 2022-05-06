package com.es.agriculturafamiliar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.es.agriculturafamiliar.dto.request.UpdateProdutorResquest;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.TipoProdutor;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.service.ICustomUserDetailsService;
import com.es.agriculturafamiliar.service.ITokenService;
import com.es.agriculturafamiliar.service.ProdutorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.models.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProdutorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProdutorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProdutorService produtorService;
    @MockBean
    EnderecoRepository enderecoRepository;

    @MockBean
    ICustomUserDetailsService<User> customUserDetailsService;

    @MockBean
    ITokenService tokenService;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    private ProdutorController produtorController;
    private Produtor produtor;
    private static ObjectMapper objectMapper;

    public static final String BASE_ENDPOINT = "/produtor";
    public static final String SAVE_ENDPOINT = "/cadastro/produtor";

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
    void saveProdutor_shouldReturnBadRequest_WhenMandatoryFieldIsMissing() throws  Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setCpfOuCnpj("");
        produtorTeste.setUser(new User());
        produtorTeste.getUser().setEmail("");


        mockMvc.perform(post(SAVE_ENDPOINT)
                        .content(objectMapper.writeValueAsString(produtorTeste))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProdutorById_shouldReturnNoContent_whenProdutorExists(){
        Assertions.assertEquals(produtorController.deleteProdutorById(produtor.getId()).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void updateEndereco_shouldReturnNoContent_whenUpdateIsSuccessful() throws Exception {
        Produtor produtorTeste = new Produtor();
        produtorTeste.setNome("teste");

        ModelMapper modelMapper = new ModelMapper();
        Assertions.assertEquals(produtorController.updateProdutor(modelMapper.map(produtorTeste, UpdateProdutorResquest.class), 1L).getStatusCode(), HttpStatus.OK);

    }

    @Test
    void findAll_shouldReturnOk_whenProdutorAreRequested() throws Exception {
        mockMvc.perform(get(BASE_ENDPOINT))
                .andExpect(status().isOk());
    }

}
