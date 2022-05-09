package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.dto.request.ConsumidorPedidoRequest;
import com.es.agriculturafamiliar.service.ITokenService;
import com.es.agriculturafamiliar.service.consumidorPedido.ConsumidorPedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ConsumidorPedidoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class ConsumidorPedidoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsumidorPedidoService useCase;

    @MockBean
    private ITokenService tokenService;

    public static final String BASE_ENDPOINT = "/consumidor/pedidos";

    @Test
    void cadastraComSucesso() throws Exception {
        var request = MockMvcRequestBuilders
                .post(BASE_ENDPOINT)
                .content(asJsonString(ConsumidorPedidoRequest.builder().
                        idConsumidor("1").idProdutor(1L).pedido("soja").build()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deletaComSucesso() throws Exception {
        var request = MockMvcRequestBuilders
                .delete(BASE_ENDPOINT)
                .param("idConsumidor", "1")
                .param("idProdutor", "1")
                .param("pedido", "soja")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deletaSemSucesso() throws Exception {
        var request = MockMvcRequestBuilders
                .delete(BASE_ENDPOINT)
                .param("idConsumidor", "")
                .param("idProdutor", "")
                .param("pedido", "")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
