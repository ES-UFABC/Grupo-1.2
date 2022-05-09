package com.es.agriculturafamiliar.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.es.agriculturafamiliar.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiMetadata());
	}

    public ApiInfo apiMetadata() {
		return new ApiInfoBuilder()
		.license("Common Licenses")
		.title("Muda API")
		.version("1.0")
		.build();		
    }
}
