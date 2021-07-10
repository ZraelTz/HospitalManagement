/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *
 * @author Zrael
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket hospitalManagementApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("Hospital Management API")
                .version("1.0")
                .description("A secure and scalable REST API for Hospital Management Systems")
                .contact(new Contact("Odunrintan Israel", "https://github.com/ZraelTz", "zraelwalker@gmail.com"))
                .license("Apache License Version 2.0")
                .build();
    }
}
