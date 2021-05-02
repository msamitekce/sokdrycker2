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

	@Bean
	public CommandLineRunner demo(UserRepository urepository) {
		return (args) -> {

			User user1 = new User();
			user1.setUsername("user");
			user1.setPasswordHash("$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
			user1.setEmail("user@gmail.com");
			user1.setRole("USER");
			user1.setApikey();
//		    User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
//			    "admin@gmail.com", "ADMIN");
			urepository.deleteAll();
			urepository.save(user1);
//		    urepository.save(user2);

//		    System.out.println(urepository.findApikeyByUsername("user"));

		}; 
	}
}
