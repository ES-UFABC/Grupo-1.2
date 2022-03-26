package com.es.agriculturafamiliar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.es.agriculturafamiliar.controller.EmailCadastroController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(EmailCadastroController.class)
class AgriculturaFamiliarApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void dummyEndpoint_shouldReturnMessage_whenGetIsPerformed() throws Exception {
		mockMvc.perform(get("/api/dummy")).andExpect(status().isOk());
	}

}
