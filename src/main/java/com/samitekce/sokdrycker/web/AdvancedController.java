package com.samitekce.sokdrycker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.samitekce.sokdrycker.domain.Ecode;
import com.samitekce.sokdrycker.domain.Product;
import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.EcodeRepository;
import com.samitekce.sokdrycker.repository.ProductRepository;
import com.samitekce.sokdrycker.repository.UserRepository;

@RestController
@RequestMapping("/adv")
public class AdvancedController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EcodeRepository ecodeRepo;

	@Autowired
	private ProductRepository productRepo;

	
	// User Management
	@GetMapping(value = "/user")
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@PostMapping(value = "/user")
	@ResponseBody
	public User addUser(@RequestBody User user) {
		userRepo.save(user);
		return user;
	}

	@DeleteMapping(value = "/user/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		userRepo.deleteById(id);
		return id + " deleted!";
	}

	// Ecode Management
	@GetMapping(value = "/ecode")
	public List<Ecode> showEcode() {
		return ecodeRepo.findAll();
	}

	// code , description
	@PostMapping(value = "/ecode")
	@ResponseBody
	public Ecode postEcode(@RequestBody Ecode ecode) {
		ecodeRepo.save(ecode);
		return ecode;
	}

	@GetMapping(value = "/ecode/{code}")
	public List<Ecode> findEcodebyCode(@PathVariable("code") String code) {
		return ecodeRepo.findByCode(code);
	}

	@DeleteMapping(value = "/ecode/{code}")
	public String deleteEcodebyCode(@PathVariable("code") String code) {
		ecodeRepo.deleteById(code);
		return code + " deleted!";
	}

	// Product Management
	@GetMapping(value = "/product")
	public List<Product> showProducts() {
		return productRepo.findAll();
	}

	@DeleteMapping(value = "/product/{ean}")
	public String deleteProductByEan(@PathVariable("ean") String ean) {
		productRepo.deleteByEan(ean);
		return ean + " deleted!";
	}

}
