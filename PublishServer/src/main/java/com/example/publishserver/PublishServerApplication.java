package com.example.publishserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PublishServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublishServerApplication.class, args);
	}

}
