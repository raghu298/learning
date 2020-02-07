package org.learning.restapi.restapi.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Config 
 * @author Biradar
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket restApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("org.learning.restapi.restapi.controller"))
	                .build()
	                .apiInfo(apiInfo());
	    }
	
	    	private ApiInfo apiInfo() {
	            return new ApiInfo("REST API specification", "This document specifies the REST API for obtaining rest api.", "v1.0.0", "Terms of service",
	                    new Contact("", "", ""), "License of API", "http://localhost:9000", Collections.emptyList());
	        
	       
	    }

}
