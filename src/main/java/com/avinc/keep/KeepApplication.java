package com.avinc.keep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeepApplication.class, args);
	}

}
