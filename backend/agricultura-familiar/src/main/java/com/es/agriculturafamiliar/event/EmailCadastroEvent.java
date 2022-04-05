package com.es.agriculturafamiliar.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmailCadastroEvent {
    private final String name;
    private final String toEmail;
        
}
