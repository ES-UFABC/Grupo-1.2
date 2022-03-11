package com.es.agriculturafamiliar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.es.agriculturafamiliar.controller.DummyController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(DummyController.class)
class AgriculturaFamiliarApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void dummyEndpoint_ShouldReturnMessage_WhenGetIsPerformed() throws Exception {
		mockMvc.perform(get("/api/dummy")).andExpect(status().isOk());
	}

}
