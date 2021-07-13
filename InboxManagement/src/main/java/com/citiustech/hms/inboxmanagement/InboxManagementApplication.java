package com.citiustech.hms.inboxmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class InboxManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InboxManagementApplication.class, args);
	}

	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
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
