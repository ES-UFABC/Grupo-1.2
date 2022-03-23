package com.es.agriculturafamiliar.services;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import com.es.agriculturafamiliar.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutorService {

    @Autowired
    private ProdutorRepository produtorRepository;

    public Produtor saveProdutor(Produtor produtor){
        return this.produtorRepository.save(produtor);
    }

    public List<Produtor> findAll(){
        return produtorRepository.findAll();
    }

    public Produtor findProdutorByCpfOuCnpj(String cpfOuCnpj){
        return this.produtorRepository.findById(cpfOuCnpj)
                .orElseThrow(() -> new ObjectNotFoundException("CPF ou CNPJ não encontrado"));
    }

    public Produtor updateProdutor(Produtor produtor, String cpfOuCnpj){
        var findProdutor = findProdutorByCpfOuCnpj(cpfOuCnpj);
        produtor.setCpfOuCnpj(findProdutor.getCpfOuCnpj());
        var updatedProdutor = produtorRepository.save(produtor);
        return updatedProdutor;
    }

    public void deleteProdutorByCpfOuCnpj(String cpfOuCnpj){
        var produtor = findProdutorByCpfOuCnpj(cpfOuCnpj);
        produtorRepository.delete(produtor);
    }
}
