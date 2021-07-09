package com.hospital.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "com.hospital.management", "com.hospital.management.service"})
@EnableAsync
@Service
public class ManagementApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
            return builder.sources(ManagementApplication.class);
        }
}