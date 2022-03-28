package com.es.agriculturafamiliar.config.cadastroconsumidor;

import com.es.agriculturafamiliar.controller.mapper.CadastroConsumidorMapper;
import com.es.agriculturafamiliar.models.usecase.cadastroconsumidor.CadastroConsumidorUseCase;
import com.es.agriculturafamiliar.repository.cadastroconsumidor.CadastroConsumidorRepository;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
@ComponentScan(basePackages = "com.es.agriculturafamiliar")
public class CadastroConsumidorConfiguration {

    @Bean
    CadastroConsumidorUseCase cadastroConsumidorUseCase(CadastroConsumidorRepository repository) {
        return new CadastroConsumidorUseCase(repository);
    }

}
