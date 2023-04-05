package com.prajjwal.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
		
	@Autowired
	BuildProperties buildProperties;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.prajjwal.restcontroller"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
				
	}
	
	
	private ApiInfo metaInfo() {
		return new ApiInfoBuilder().title("Api for Employee and Company").version(buildProperties.getVersion()).build();
	}
}
