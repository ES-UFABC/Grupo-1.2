package com.es.agriculturafamiliar.controller;

import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        var produto = produtoService.findProdutoById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        var produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/tipo/{tipologiaCod}")
    public ResponseEntity<List<Produto>> findAllByTipologia(@PathVariable Integer tipologiaCod){
        var produtos = produtoService.findProdutosByTipologia(tipologiaCod);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> findAllByNome(@RequestParam String nomeProduto){
        var produtos = produtoService.findProdutosByNome(nomeProduto);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@Valid @RequestBody Produto produto){
        produto = produtoService.saveProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto, @PathVariable Long id){
        var produtoAtualizado = produtoService.updateProduto(produto, id);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutoById(@PathVariable Long id) {
        produtoService.deleteProdutoById(id);
        return ResponseEntity.noContent().build();
    }
}
