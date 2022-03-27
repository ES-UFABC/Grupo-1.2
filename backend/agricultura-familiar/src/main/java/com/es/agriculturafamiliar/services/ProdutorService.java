package com.es.agriculturafamiliar.services;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.event.EmailCadastroEvent;
import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.ProdutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutorService {

    @Autowired
    private ProdutorRepository produtorRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public Produtor saveProdutor(Produtor produtor){
        produtor = produtorRepository.save(produtor);
        applicationEventPublisher.publishEvent(new EmailCadastroEvent(produtor.getNome(), produtor.getEmail()));
        return this.produtorRepository.save(produtor);
    }

    public List<Produtor> findAll(){
        return produtorRepository.findAll();
    }

    public Produtor findProdutorById(Long id){
        return this.produtorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produtor was not found"));
    }

    public Produtor updateProdutor(Produtor produtor, Long id){
        var findProdutor = findProdutorById(id);
        produtor.setId(findProdutor.getId());
        var updatedProdutor = produtorRepository.save(produtor);
        return updatedProdutor;
    }

    public void deleteProdutorById(Long id){
        var produtor = findProdutorById(id);
        produtorRepository.delete(produtor);
    }
}
