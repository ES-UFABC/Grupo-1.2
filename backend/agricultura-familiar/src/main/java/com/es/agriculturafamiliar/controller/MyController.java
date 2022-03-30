package com.es.agriculturafamiliar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @GetMapping("/eae")
    public ResponseEntity<?> getMyStuff() {
        return ResponseEntity.ok("13213123");
    }
}
