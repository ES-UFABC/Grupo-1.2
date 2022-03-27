package com.es.agriculturafamiliar.endereco.controller;

import com.es.agriculturafamiliar.controller.EnderecoController;
import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.enums.TipoEndereco;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.service.EnderecoService;
import com.es.agriculturafamiliar.service.ProdutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springfox.documentation.swagger2.mappers.ModelMapper;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private EnderecoService enderecoService;
    @MockBean
    private EnderecoRepository enderecoRepository;
    @MockBean
    private ProdutorService produtorService;

    public static final String BASE_ENDPOINT = "/api/v1/endereco";
    private static ObjectMapper objectMapper;

    @BeforeAll
    private static void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void findById_shouldReturnStatusOk_whenEnderecoExists() throws Exception {
        Endereco endereco = new Endereco(null, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);
        when(enderecoService.findById(any(Long.class))).thenReturn(endereco);

        mockMvc.perform(get(BASE_ENDPOINT + "/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bairro", Matchers.is(endereco.getBairro())))
                .andExpect(jsonPath("$.cep", Matchers.is(endereco.getCep())))
                .andExpect(jsonPath("$.rua", Matchers.is(endereco.getRua())));
    }

    @Test
    void saveEndereco_shouldReturnStatusCreated_whenValidRequestBodyIsReceived() throws Exception {
        Endereco endereco = new Endereco(1L, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        when(enderecoService.saveEndereco(any(Endereco.class)))
                .thenReturn(endereco);

        Endereco savedEndereco = new Endereco(null, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);
        mockMvc.perform(post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedEndereco)))
                .andExpect(status().isCreated());
    }


    @Test
    void saveEndereco_shouldReturnBadRequest_whenMandatoryFieldIsMissing() throws JsonProcessingException, Exception {
        Endereco endereco = new Endereco(null, null,null,null,null,null, TipoEndereco.PRODUCAO, null);

        mockMvc.perform(post(BASE_ENDPOINT)
                        .content(objectMapper.writeValueAsString(endereco))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEnderecoById_shouldReturnNoContent_whenEnderecoExists() throws Exception {
        Endereco endereco = new Endereco(1L, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        when(enderecoService.saveEndereco(any(Endereco.class)))
                .thenReturn(endereco);

        Endereco savedEndereco = new Endereco(null, "rua","10","bairro","cep","municipio", TipoEndereco.PRODUCAO, null);

        mockMvc.perform(post(BASE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedEndereco)));

        mockMvc.perform(delete(BASE_ENDPOINT + "/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
