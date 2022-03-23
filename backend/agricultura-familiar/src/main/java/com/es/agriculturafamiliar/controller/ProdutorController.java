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

    @GetMapping("/{cpfOuCnpj}")
    public ResponseEntity<Produtor> findByCpfOuCnpj(@PathVariable String cpfOuCnpj){
        var produtor = produtorService.findProdutorByCpfOuCnpj(cpfOuCnpj);
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

    @PutMapping("/{cpfOuCnpj}")
    public ResponseEntity<Produtor> updateProdutor(@RequestBody Produtor produtor, @PathVariable String cpfOuCnpj){
        var produtorAtualizado = produtorService.updateProdutor(produtor, cpfOuCnpj);
        return ResponseEntity.ok(produtorAtualizado);
    }

    @DeleteMapping("/{cpfOuCnpj}")
    public ResponseEntity<?> deleteProdutor(@PathVariable String cpfOuCnpj) {
        produtorService.deleteProdutorByCpfOuCnpj(cpfOuCnpj);
        return ResponseEntity.noContent().build();
    }

}
