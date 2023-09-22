package com.oliveira.erudio.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTfull API with Java 17 and Spring Boot 3")
                        .version("v1")
                        .description("Description about your API")
                        .termsOfService("http://pub.erudio.com.br/meus-cursos")
                        .license(new License().name("Apache 2.0")
                                .url("http://pub.erudio.com.br/meus-cursos")));
    }
}