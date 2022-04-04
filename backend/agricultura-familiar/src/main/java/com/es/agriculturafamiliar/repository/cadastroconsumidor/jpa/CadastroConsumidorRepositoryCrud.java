package com.es.agriculturafamiliar.repository.cadastroconsumidor.jpa;

import com.es.agriculturafamiliar.entity.cadastroconsumidor.CadastroConsumidorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroConsumidorRepositoryCrud extends JpaRepository<CadastroConsumidorEntity, String>{

}
