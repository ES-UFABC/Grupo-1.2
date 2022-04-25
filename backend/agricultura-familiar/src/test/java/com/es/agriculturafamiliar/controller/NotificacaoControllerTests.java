package com.es.agriculturafamiliar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.es.agriculturafamiliar.dto.NotificacaoDTO;
import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.service.ICustomUserDetailsService;
import com.es.agriculturafamiliar.service.ITokenService;
import com.es.agriculturafamiliar.service.NotificacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificacaoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NotificacaoControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private ModelMapper modelMapper;

	@MockBean
	private NotificacaoService notificationService;
	
	@MockBean
    private ICustomUserDetailsService<User> customUserDetailsService;

    @MockBean
    private ITokenService tokenService;

	private static ObjectMapper objectMapper;
	public static final String BASE_ENDPOINT = "/notificacao";
	public static final String ADMIN_ENDPOINT = "/notificacao/admin";

	@BeforeAll
	private static void setup() {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	void findById_shouldReturnStatusOk_whenNotificationExists() throws Exception {
		Notificacao notificacao = Notificacao.builder()
			.assunto("Manutenção")
            .mensagem("O sistema vai entrar em manutenção")            
            .dataPublicacao(LocalDateTime.now())
			.build();

		when(notificationService.findNotificacaoById(any(Long.class))).thenReturn(notificacao);		
		
		mockMvc.perform(get(BASE_ENDPOINT + "/{id}", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.assunto", Matchers.is(notificacao.getAssunto())))
			.andExpect(jsonPath("$.mensagem", Matchers.is(notificacao.getMensagem())));
	}

	@Test
	void findById_shouldReturnStatusBadRequest_whenNotificationDoesNotExists() throws Exception {
		when(notificationService.findNotificacaoById(any(Long.class))).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(BASE_ENDPOINT + "/1"))
			.andExpect(status().isNotFound());
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
			.thenReturn(notificacao);

		mockMvc.perform(post(ADMIN_ENDPOINT)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(notificacaoDTO)))
			.andExpect(status().isCreated());			
	}

	@Test
	void saveNotificacao_shouldReturnBadRequest_whenMandatoryFieldIsMissing() throws JsonProcessingException, Exception {
		NotificacaoDTO notificacao = NotificacaoDTO.builder()
			.build();

		mockMvc.perform(post(ADMIN_ENDPOINT)
			.content(objectMapper.writeValueAsString(notificacao))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());			
	}

	@Test
	void deleteNotificacaoById_shouldReturnNoContent_whenNotificacaoExists() throws Exception {
		Notificacao notificacao = new Notificacao();

		when(notificationService.deleteById(any(Long.class)))
			.thenReturn(notificacao);

		mockMvc.perform(delete(ADMIN_ENDPOINT + "/{id}", "123"))
			.andExpect(status().isNoContent());
	}

	@Test
	void deleteNotificacaoById_shouldNotFound_whenNotificacaoDoesNotExists() throws Exception {
		when(notificationService.deleteById(any(Long.class)))
			.thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(delete(ADMIN_ENDPOINT + "/123"))
			.andExpect(status().isNotFound());
	}

	@Test
	void updateNotificacao_shouldReturnNoContent_whenUpdateIsSuccessful() throws Exception {
		NotificacaoDTO novaNotificacao = NotificacaoDTO.builder().assunto("Novo assunto")
			.mensagem("Olá que tá").build();

		Notificacao notificacaoAtualizada = new Notificacao();

		when(notificationService.update(any(), any(Long.class)))
			.thenReturn(notificacaoAtualizada);

		mockMvc.perform(put(ADMIN_ENDPOINT + "/123")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(novaNotificacao)))
			.andExpect(status().isOk());
	}

	@Test
	void updateNotificacao_shouldNotFound_whenUpdateFailsBecauseResourceNotExists() throws Exception {
		NotificacaoDTO novaNotificacao = NotificacaoDTO.builder().assunto("Novo assunto")
			.mensagem("Olá que tá").build();

		when(notificationService.update(any(), any(Long.class)))
			.thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(put(ADMIN_ENDPOINT + "/{id}", "123")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(novaNotificacao)))
			.andExpect(status().isNotFound());
	}

	@Test
	void findAll_shouldReturnOk_whenNotificacoesAreRequested() throws Exception {
		when(notificationService.findAll(any())).thenReturn(Page.empty());
		mockMvc.perform(get(BASE_ENDPOINT))
			.andExpect(status().isOk());
	}

}
