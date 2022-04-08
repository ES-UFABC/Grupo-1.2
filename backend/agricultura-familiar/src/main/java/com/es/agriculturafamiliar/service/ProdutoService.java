package com.es.agriculturafamiliar.service;

import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.enums.Tipologia;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutorService produtorService;

    public Produto findProdutoById(Long id){
        var produto = produtoRepository.findById(id);
        return produto.orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado"));
    }

    public List<Produto> findProdutosByTipologia(Integer tipologiaCod){
        var produtos = produtoRepository.findByTipologia(tipologiaCod);
        return produtos;
    }

    public List<Produto> findAll(){
        var produtos = produtoRepository.findAll();
        return produtos;
    }

    public Produto saveProduto(Produto produto){
        if (produto.getProdutor() == null)
            throw new ResourceNotFoundException("Produtor inválido ou não encontrado");

        produto = produtoRepository.save(produto);
        return produto;
    }

    public void deleteProdutoById(Long id){
        var produto = findProdutoById(id);
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Produto produto, Long id){
        var oldProduto = findProdutoById(id);
        produto.setProdutor(produtorService.findProdutorById(id));
        return produtoRepository.save(produto);
    }

}
