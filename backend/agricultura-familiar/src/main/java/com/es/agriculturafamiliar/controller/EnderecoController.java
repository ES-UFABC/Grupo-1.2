package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/produtor/{produtorId}")
    public ResponseEntity<List<Endereco>> findByProdutorId(@PathVariable Long produtorId){
        var enderecos = enderecoService.findByProdutorId(produtorId);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        var endereco = enderecoService.findById(id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> saveEndereco(@Valid @RequestBody Endereco endereco){
        endereco = enderecoService.saveEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnderecoById(@PathVariable Long id){
        enderecoService.deleteEnderecoById(id);
        return ResponseEntity.noContent().build();
    }
}
