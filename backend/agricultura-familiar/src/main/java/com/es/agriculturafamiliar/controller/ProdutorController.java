package com.es.agriculturafamiliar.controller;

import java.util.List;

import javax.validation.Valid;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.service.ProdutoService;
import com.es.agriculturafamiliar.service.ProdutorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController()
@RequestMapping("/cadastro/produtor")
@AllArgsConstructor
public class ProdutorController {
    
    private final ProdutorService produtorService;

    @Autowired
    private ProdutoService produtoService;

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

    @GetMapping("/geolocalizacao")
    public ResponseEntity<List<Produtor>> findByCidade(@RequestParam String estado, @RequestParam String municipio){
        var produtor = produtorService.findByLocalizacao(estado, municipio);
        return ResponseEntity.ok(produtor);
    }

    @PostMapping
    public ResponseEntity<Produtor> saveProdutor(@Valid @RequestBody Produtor produtor){
        Produtor savedProdutor = produtorService.saveProdutor(produtor, produtor.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProdutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtor> updateProdutor(@Valid @RequestBody Produtor produtor, @PathVariable Long id){
        var produtorAtualizado = produtorService.updateProdutor(produtor, id);
        return ResponseEntity.ok(produtorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutorById(@PathVariable Long id) {
        produtorService.deleteProdutorById(id);
        return ResponseEntity.noContent().build();
    }

}
