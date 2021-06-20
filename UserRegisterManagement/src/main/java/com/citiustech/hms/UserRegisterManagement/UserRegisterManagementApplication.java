package com.citiustech.hms.UserRegisterManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@CrossOrigin(origins = "http://localhost:4000/", allowedHeaders = "*")

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class UserRegisterManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegisterManagementApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer corsOriginConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedHeaders("*")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}

}
