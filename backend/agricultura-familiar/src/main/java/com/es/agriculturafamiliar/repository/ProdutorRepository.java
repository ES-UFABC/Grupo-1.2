package com.es.agriculturafamiliar.repository;

import com.es.agriculturafamiliar.entity.produtor.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutorRepository extends JpaRepository<Produtor, String> {
}
