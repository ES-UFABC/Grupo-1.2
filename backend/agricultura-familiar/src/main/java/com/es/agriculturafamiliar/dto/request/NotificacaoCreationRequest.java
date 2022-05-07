package com.es.agriculturafamiliar.dto.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "notificacao")
public class NotificacaoCreationRequest {

    @NotEmpty
    private String assunto;

    @NotEmpty
    private String mensagem;
    
    private Long adminId;
}
