package com.castgroupteste.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan({"com.castgroupteste.banco"})
@EnableJpaRepositories({"com.castgroupteste.banco"})
@ComponentScan(basePackages = {"com.castgroupteste.banco"})
@SpringBootApplication(scanBasePackages = "com.castgroupteste.banco")
public class CastgrouptesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CastgrouptesteApplication.class, args);
	}

}
