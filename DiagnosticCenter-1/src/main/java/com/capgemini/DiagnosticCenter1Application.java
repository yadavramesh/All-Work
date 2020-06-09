package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class DiagnosticCenter1Application {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticCenter1Application.class, args);
	}
	  @Bean
	    public Docket SwaggerConfigure() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                .apis(RequestHandlerSelectors.basePackage("com.capgemini.controller"))
	 .build().apiInfo(apiDetails());
	    }
	  
	  @SuppressWarnings("deprecation")
	public ApiInfo apiDetails()
	  {
		  return new ApiInfo("Health Care Management System", "Here we have different diagnostic Center which consist of different lab test ", "version 1.0.2", "Free to use", "8956234512", "HealthCare.licence", "http//:care.com");
	  }
	
}
