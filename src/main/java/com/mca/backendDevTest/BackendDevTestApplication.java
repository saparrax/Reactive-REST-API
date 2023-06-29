package com.mca.backendDevTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BackendDevTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDevTestApplication.class, args);
	}

}
