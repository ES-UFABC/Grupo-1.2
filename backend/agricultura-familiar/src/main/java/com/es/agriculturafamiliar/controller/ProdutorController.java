package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.services.ProdutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/v1/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorService produtorService;

    @GetMapping("/{cpfOuCnpj}")
    public ResponseEntity<Produtor> findByCpfOuCnpj(@PathVariable String cpfOuCnpj){
        var produtor = produtorService.searchByCpfOuCnpj(cpfOuCnpj);
        return ResponseEntity.ok(produtor);
    }

    @GetMapping
    public ResponseEntity<List<Produtor>> findAll(){
        var produtores = produtorService.findAll();
        return ResponseEntity.ok(produtores);
    }

    @PostMapping
    public ResponseEntity<Produtor> saveProdutor(@RequestBody Produtor produtor){
        produtor = produtorService.saveProdutor(produtor);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtor> updateProdutor(@RequestBody Produtor produtor, @PathVariable String cpfOuCnpj){
        var produtorAtualizado = produtorService.updateProdutor(produtor, cpfOuCnpj);
        return ResponseEntity.ok(produtorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutor(@PathVariable String cpfOuCnpj){
        return ResponseEntity.noContent().build();
    }

}
