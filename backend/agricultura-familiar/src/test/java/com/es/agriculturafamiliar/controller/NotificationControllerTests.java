package com.es.agriculturafamiliar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import com.es.agriculturafamiliar.entity.Notificacao;
import com.es.agriculturafamiliar.service.NotificationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.hamcrest.Matchers;


@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	public static final String BASE_ENDPOINT = "/api/v1/admin/notifications";

	@MockBean
	private NotificationService notificationService;
	
	@Test
	void findById_ShouldReturnStatusOk_WhenNotificationExists() throws Exception {

		Optional<Notificacao> notificacaoOptional = Optional.of(
			Notificacao.builder()
			.assunto("Manutenção")
            .mensagem("O sistema vai entrar em manutenção")
            .dataPublicacao(LocalDateTime.MAX)
			.build()
			);

		when(notificationService.findNotificacaoById(any(Long.class))).thenReturn(notificacaoOptional);

		Notificacao notificacao = notificacaoOptional.get();
		
		mockMvc.perform(get(BASE_ENDPOINT).param("id", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.assunto", Matchers.is(notificacao.getAssunto())))
			.andExpect(jsonPath("$.mensagem", Matchers.is(notificacao.getMensagem())))
			.andExpect(jsonPath("$.dataPublicacao", Matchers.is(notificacao.getDataPublicacao().toString())));
	}

	@Test
	void findById_ShouldReturnStatusBadRequest_WhenNotificationDoesNotExists() throws Exception {
		Optional<Notificacao> emptyNotificacao = Optional.empty();

		when(notificationService.findNotificacaoById(any(Long.class))).thenReturn(emptyNotificacao);

		mockMvc.perform(get(BASE_ENDPOINT).param("id", "1"))
			.andExpect(status().isBadRequest());
	}

}
