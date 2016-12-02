package com.electromagneticsoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.electromagneticsoftware.business.entities.Light;
import com.electromagneticsoftware.business.entities.repositories.LightRepository;
import com.electromagneticsoftware.business.entities.repositories.LightRepositoryImpl;

@SpringBootApplication
public class HueappApplication {
	
	private static final Logger log = LoggerFactory.getLogger(HueappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HueappApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public LightRepository lightRepository() {
		return new LightRepositoryImpl();
	}
	
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			Light light = restTemplate.getForObject("http://192.168.2.111/api/3f451a9f534fab0714d7b6bc27208ba3/lights/5", Light.class);
//			log.info(light.toString());
//		};
//	}
	
}
