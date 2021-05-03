package com.samitekce.sokdrycker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.UserRepository;

@SpringBootApplication
public class SokdryckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SokdryckerApplication.class, args);
	}

}
