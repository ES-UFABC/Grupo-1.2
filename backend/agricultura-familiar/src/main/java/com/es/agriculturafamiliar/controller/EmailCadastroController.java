package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/dummy")
@AllArgsConstructor
public class EmailCadastroController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/email")
    public ResponseEntity<?> sendEMail(@RequestParam String name, @RequestParam String toEmail) {
        applicationEventPublisher.publishEvent(new EmailCadastroEvent(name, toEmail));
        return ResponseEntity.ok().build();
    }
}
