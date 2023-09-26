package com.vigyanshaala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class VigyaanshalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VigyaanshalaApplication.class, args);
	}

}
