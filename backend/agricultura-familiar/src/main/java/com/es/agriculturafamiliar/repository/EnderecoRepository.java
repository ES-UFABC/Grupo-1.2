package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<List<Endereco>> findByProdutorId(Long produtorId);


}
