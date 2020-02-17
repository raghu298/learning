package org.learning.poc.scylla.springbootscyllaproducer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 2 config class
 *
 * @author epxaxnx
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.learning.poc.scylla.springbootscyllaproducer.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());


    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Usage Reports API specification", "This document specifies the REST API for obtaining Device data.", "v1.0.0", "Terms of service",
                new Contact("", "", ""), "License of API", "http://localhost:9003", Collections.emptyList());
    }
}