package com.nj.parcc.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration settings
 * @author Sriram
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("PARCC Service", "NJ Parcc Student Data", "1.0", null,
				new Contact("NJ Educational Agency", null, null), null, null);
		return apiInfo;
	}
	
}