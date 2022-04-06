package com.es.agriculturafamiliar;

import com.es.agriculturafamiliar.entity.Produto;
import com.es.agriculturafamiliar.enums.Tipologia;
import com.es.agriculturafamiliar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableRetry
@SpringBootApplication
public class AgriculturaFamiliarApplication{
	public static void main(String[] args) {
		SpringApplication.run(AgriculturaFamiliarApplication.class, args);
	}

	@Autowired
	ProdutoService produtoService;
}
