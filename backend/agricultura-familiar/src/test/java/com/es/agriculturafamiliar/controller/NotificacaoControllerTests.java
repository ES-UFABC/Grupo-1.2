package com.es.agriculturafamiliar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import com.es.agriculturafamiliar.dto.NotificacaoDTO;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.service.NotificacaoService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.hamcrest.Matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificacaoController.class)
public class NotificacaoControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private ModelMapper modelMapper;

	public static final String BASE_ENDPOINT = "/api/v1/admin/notifications";

	@MockBean
	private NotificacaoService notificationService;

	private static ObjectMapper objectMapper;

	@BeforeAll
	private static void setup() {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	void findById_shouldReturnStatusOk_whenNotificationExists() throws Exception {
		Optional<Notificacao> notificacaoOptional = Optional.of(
			Notificacao.builder()
			.assunto("Manutenção")
            .mensagem("O sistema vai entrar em manutenção")
            .dataPublicacao(LocalDateTime.MAX)
			.build()
			);

		when(notificationService.findNotificacaoById(any(Long.class))).thenReturn(notificacaoOptional);

		Notificacao notificacao = notificacaoOptional.get();
		
		mockMvc.perform(get(BASE_ENDPOINT + "/{id}", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.assunto", Matchers.is(notificacao.getAssunto())))
			.andExpect(jsonPath("$.mensagem", Matchers.is(notificacao.getMensagem())))
			.andExpect(jsonPath("$.dataPublicacao", Matchers.is(notificacao.getDataPublicacao().toString())));
	}

	@Test
	void findById_shouldReturnStatusBadRequest_whenNotificationDoesNotExists() throws Exception {
		Optional<Notificacao> emptyNotificacao = Optional.empty();

		when(notificationService.findNotificacaoById(any(Long.class))).thenReturn(emptyNotificacao);

		mockMvc.perform(get(BASE_ENDPOINT + "/{id}", "1"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void saveNotificacao_shouldReturnStatusCreated_whenValidRequestBodyIsReceived() throws Exception {
		NotificacaoDTO notificacaoDTO = NotificacaoDTO.builder().assunto("Manutenção")
			.mensagem("Deu ruim")
			.build();
		
		Notificacao notificacao = Notificacao.builder()
			.dataPublicacao(LocalDateTime.MAX)
			.id(32l)
			.mensagem("Mensagem cadastrada")
			.assunto("Manutenção")
			.build();

		when(notificationService.saveNotificacao(any(Notificacao.class)))
			.thenReturn(Optional.of(notificacao));

		mockMvc.perform(post(BASE_ENDPOINT)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(notificacaoDTO)))
			.andExpect(status().isCreated());			
	}

	@Test
	void saveNotificacao_shouldReturnBadRequest_whenMandatoryFieldIsMissing() throws JsonProcessingException, Exception {
		NotificacaoDTO notificacao = NotificacaoDTO.builder()
			.build();

		mockMvc.perform(post(BASE_ENDPOINT)
			.content(objectMapper.writeValueAsString(notificacao))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());			
	}

	@Test
	void deleteNotificacaoById_shouldReturnNoContent_whenNotificacaoExists() throws Exception {
		Optional<Notificacao> notificacao = Optional.of(new Notificacao());

		when(notificationService.deleteById(any(Long.class)))
			.thenReturn(notificacao);

		mockMvc.perform(delete(BASE_ENDPOINT + "/{id}", "123"))
			.andExpect(status().isNoContent());
	}

	@Test
	void deleteNotificacaoById_shouldReturnBadRequest_whenNotificacaoDoesNotExists() throws Exception {
		Optional<Notificacao> notificacao = Optional.empty();

		when(notificationService.deleteById(any(Long.class)))
			.thenReturn(notificacao);

		mockMvc.perform(delete(BASE_ENDPOINT + "/{id}", "123"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void updateNotificacao_shouldReturnNoContent_whenUpdateIsSuccessful() throws Exception {
		NotificacaoDTO novaNotificacao = NotificacaoDTO.builder().assunto("Novo assunto")
			.mensagem("Olá que tá").build();

		Optional<Notificacao> notificacaoAtualizada = Optional.of(new Notificacao());

		when(notificationService.update(any(), any(Long.class)))
			.thenReturn(notificacaoAtualizada);

		mockMvc.perform(put(BASE_ENDPOINT + "/{id}", "123")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(novaNotificacao)))
			.andExpect(status().isOk());
	}

	@Test
	void updateNotificacao_shouldReturnBadRequest_whenUpdateFails() throws Exception {
		NotificacaoDTO novaNotificacao = NotificacaoDTO.builder().assunto("Novo assunto")
			.mensagem("Olá que tá").build();

		Optional<Notificacao> notificacaoAtualizada = Optional.empty();

		when(notificationService.update(any(), any(Long.class)))
			.thenReturn(notificacaoAtualizada);

		mockMvc.perform(put(BASE_ENDPOINT + "/{id}", "123")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(novaNotificacao)))
			.andExpect(status().isBadRequest());
	}

	@Test
	void findAll_shouldReturnOk_whenNotificacoesAreRequested() throws Exception {
		mockMvc.perform(get(BASE_ENDPOINT))
			.andExpect(status().isOk());
	}

}
