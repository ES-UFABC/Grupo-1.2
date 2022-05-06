package com.es.agriculturafamiliar.controller;

import java.util.List;

import javax.validation.Valid;

import com.es.agriculturafamiliar.dto.request.UpdateProdutorResquest;
import com.es.agriculturafamiliar.dto.response.ProdutorPerfilResponse;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.service.ProdutorService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;


@RestController()
@RequestMapping("/produtor")
@AllArgsConstructor
public class ProdutorController {

    @Autowired
    private final ProdutorService produtorService;

    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ProdutorPerfilResponse> findProdutorPerfilById(@PathVariable Long id){
        Produtor produtor = produtorService.findProdutorById(id);
        var produtoPerfilDto = modelMapper.map(produtor, ProdutorPerfilResponse.class);
        return ResponseEntity.ok(produtoPerfilDto);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutorPerfilResponse>> findAll(){
        var produtores = produtorService.findAll();
        List<ProdutorPerfilResponse> entityToDto = modelMapper.map(produtores, new TypeToken<List<ProdutorPerfilResponse>>(){}.getType());

        return ResponseEntity.ok(entityToDto);
    }

    @GetMapping("/geolocalizacao")
    public ResponseEntity<List<Produtor>> findByCidade(@RequestParam String estado, @RequestParam String municipio){
        var produtor = produtorService.findByLocalizacao(estado, municipio);
        return ResponseEntity.ok(produtor);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Produtor>> findByNomeFantasia(@RequestParam String nomeFantasia){
        var produtor = produtorService.findByNomeFantasia(nomeFantasia);
        return ResponseEntity.ok(produtor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProdutor(@Valid @RequestBody UpdateProdutorResquest updateProdutorResquest, @PathVariable Long id){
        var produtor = modelMapper.map(updateProdutorResquest, Produtor.class);
        var produtorAtualizado = produtorService.updateProdutor(produtor, id);
        return ResponseEntity.ok(updateProdutorResquest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutorById(@PathVariable Long id) {
        produtorService.deleteProdutorById(id);
        return ResponseEntity.noContent().build();
    }

}
