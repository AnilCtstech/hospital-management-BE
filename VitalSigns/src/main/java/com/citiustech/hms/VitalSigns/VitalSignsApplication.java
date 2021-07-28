package com.citiustech.hms.VitalSigns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VitalSignsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalSignsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsOriginConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
						//.allowCredentials(true);
			}
		};
	}
}
