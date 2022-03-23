package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.services.ProdutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
@RequestMapping("/api/v1/produtor")
public class ProdutorController {

    @Autowired
    private ProdutorService produtorService;

    @GetMapping("/{id}")
    public ResponseEntity<Produtor> findById(@PathVariable Long id){
        var produtor = produtorService.findProdutorById(id);
        return ResponseEntity.ok(produtor);
    }

    @GetMapping
    public ResponseEntity<List<Produtor>> findAll(){
        var produtores = produtorService.findAll();
        return ResponseEntity.ok(produtores);
    }

    @PostMapping
    public ResponseEntity<Produtor> saveProdutor(@Valid @RequestBody Produtor produtor){
        produtor = produtorService.saveProdutor(produtor);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtor> updateProdutor(@RequestBody Produtor produtor, @PathVariable Long id){
        var produtorAtualizado = produtorService.updateProdutor(produtor, id);
        return ResponseEntity.ok(produtorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutorById(@PathVariable Long id) {
        produtorService.deleteProdutorById(id);
        return ResponseEntity.noContent().build();
    }

}
