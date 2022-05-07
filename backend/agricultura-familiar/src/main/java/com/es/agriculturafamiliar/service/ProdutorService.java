package com.es.agriculturafamiliar.service;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import com.es.agriculturafamiliar.entity.ConfirmacaoCadastro;
import com.es.agriculturafamiliar.entity.Role;
import com.es.agriculturafamiliar.entity.User;
import com.es.agriculturafamiliar.entity.produtor.Produtor;
import com.es.agriculturafamiliar.enums.RoleType;
import com.es.agriculturafamiliar.event.EmailCadastroConfirmacaoPendenteEvent;

import com.es.agriculturafamiliar.exception.ResourceNotFoundException;
import com.es.agriculturafamiliar.repository.ProdutorRepository;

import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutorService {

    private final ProdutorRepository produtorRepository;
    private final ICustomUserDetailsService<User> userDetailsService;
    private final ConfirmacaoCadastroService confirmacaoCadastroService;

    private final ApplicationEventPublisher applicationEventPublisher;
    private static final Set<Role> PRODUTOR_ROLES =  Set.of(Role.builder().role(RoleType.PRODUTOR).build());

    @Transactional
    public Produtor saveProdutor(Produtor produtor, User user){
    	ConfirmacaoCadastro confirmacaoCadastro = confirmacaoCadastroService.createConfirmacaoCadastro();
    	
    	user.setConfirmacaoCadastro(confirmacaoCadastro);
        user.setRoles(PRODUTOR_ROLES);
        
        User createUser = userDetailsService.createUser(user);
  
        produtor.setUser(createUser);
        produtor = produtorRepository.save(produtor);
        
        var emailCadastroConfirmacaoPendenteEvent = EmailCadastroConfirmacaoPendenteEvent.builder()
        	.codigoConfirmacao(confirmacaoCadastro.getCodigo())
        	.name(produtor.getNome())
        	.toEmail(user.getEmail())
        	.build();
        
        applicationEventPublisher.publishEvent(emailCadastroConfirmacaoPendenteEvent);
        return this.produtorRepository.save(produtor);
    }

    public List<Produtor> findAll(){
        return produtorRepository.findAll();
    }

    public List<Produtor> findByLocalizacao(String estado, String cidade){
        return produtorRepository.findByEnderecosEstadoAndEnderecosMunicipio(estado, cidade);
    }

    public List<Produtor> findByNomeFantasia(String nomeFantasia){
        return produtorRepository.findByNomeFantasiaAproximado(nomeFantasia);
    }

    public Produtor findProdutorById(Long id){
        return this.produtorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produtor was not found"));
    }

    @Transactional
    public Produtor updateProdutor(Produtor produtor, Long id){
        var findProdutor = findProdutorById(id);
        findProdutor.setTipoProdutor(produtor.getTipoProdutor());
        findProdutor.setOrganico(produtor.getOrganico());
        findProdutor.setNomeFantasia(produtor.getNomeFantasia());
        findProdutor.setRegiaoDeProducao(produtor.getRegiaoDeProducao());
        findProdutor.setAtendeNoEnderecoDeProducao(produtor.getAtendeNoEnderecoDeProducao());
        findProdutor.setCadastroEntidade(produtor.getCadastroEntidade());
        findProdutor.setTipoProdutor(produtor.getTipoProdutor());
        findProdutor.setRegistroOuCertificacao(produtor.getRegistroOuCertificacao());
        findProdutor.setCertificacaoAgroecologico(produtor.getCertificacaoAgroecologico());
        findProdutor.setAgroecologico(produtor.getAgroecologico());
        findProdutor.setOrganico(produtor.getOrganico());
        findProdutor.setEntidadesAtendidas(produtor.getEntidadesAtendidas());
        findProdutor.setTiposProducao(produtor.getTiposProducao());
        findProdutor.setTelefones(produtor.getTelefones());
        findProdutor.setFormasPagamento(produtor.getFormasPagamento());
        findProdutor.setRegistrosOuCertificacoes(produtor.getRegistrosOuCertificacoes());
        findProdutor.setPaginasExternas(produtor.getPaginasExternas());

        var updatedProdutor = produtorRepository.save(findProdutor);
        return updatedProdutor;

    }

    public void deleteProdutorById(Long id){
        var produtor = findProdutorById(id);
        produtorRepository.delete(produtor);
    }

    public Optional<Produtor> consultaPorUserId(Long id) {
        return produtorRepository.findProdutorByUserId(id);
    }
}
