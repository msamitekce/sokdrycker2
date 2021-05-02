package com.samitekce.sokdrycker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samitekce.sokdrycker.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	String findApikeyByUsername(String apikey);

	User findByApikey(String apikey);

	User findUserByApikey(String apikey);
	
}
