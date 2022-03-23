package com.es.agriculturafamiliar.services;

import com.es.agriculturafamiliar.entity.Endereco;
import com.es.agriculturafamiliar.repository.EnderecoRepository;
import com.es.agriculturafamiliar.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco saveEndereco(Endereco endereco){
        return this.enderecoRepository.save(endereco);
    }

    public List<Endereco> findByProdutorId(Long produtorId){
        return this.enderecoRepository.findByProdutorId(produtorId).orElseThrow(() ->
                new ObjectNotFoundException("Falha ao buscar endereços do produtor"));
    }

    public Endereco findById(Long id){
        return this.enderecoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Endereço não encontrado!"));
    }

    public void deleteEnderecoById(Long id){
        var endereco = findById(id);
        enderecoRepository.deleteById(id);
    }
}
