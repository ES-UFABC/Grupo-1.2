package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.Notificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends PagingAndSortingRepository<Notificacao, Long>{
    
}
