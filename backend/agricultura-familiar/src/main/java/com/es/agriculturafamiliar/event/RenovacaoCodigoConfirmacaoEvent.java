package com.es.agriculturafamiliar.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RenovacaoCodigoConfirmacaoEvent {

    private final String toEmail;
    private final String codigoConfirmacao;
}
