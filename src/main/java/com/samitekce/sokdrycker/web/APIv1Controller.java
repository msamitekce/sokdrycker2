package com.samitekce.sokdrycker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samitekce.sokdrycker.domain.Ecode;
import com.samitekce.sokdrycker.domain.Product;
import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.EcodeRepository;
import com.samitekce.sokdrycker.repository.ProductRepository;
import com.samitekce.sokdrycker.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class APIv1Controller {

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private UserRepository userRepo;

	// Find all products
	@GetMapping(value = "/product", params = { "apikey" })
	public List<Product> allProducts(@RequestParam(value = "apikey", required = true) String apikey) {
		if (userRepo.findByApikey(apikey).getUserStatus().equals("USER")) {
			return proRepo.findAll();
		}

		return null;
	}

	// Find all products and order by sugar, EAN or name
	@GetMapping(value = "/product", params = { "apikey", "order" })
	public List<Product> findProductsByOrder(@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "apikey", required = true) String apikey) {
		if (userRepo.findByApikey(apikey).getUserStatus().equals("USER")) {

			if (!order.equals("")) {
				return proRepo.findAll((Sort.by(Sort.Direction.ASC, order)));
			}
			return proRepo.findAll();
		}
		return null;
	}

	// Find Product by EAN
	@GetMapping(value = "/product", params = { "apikey", "ean" })
	public List<Product> findProductsByEan(@RequestParam(value = "ean", required = true) String ean,
			@RequestParam(value = "apikey", required = true) String apikey) {
		return proRepo.findByEan(ean);
	}

	// Find product by Ecode and contains = 0 or 1
	@GetMapping(value = "/product", params = { "apikey", "ecode", "contains" })
	public List<Product> findProductsByEcode(@RequestParam(value = "ecode", required = false) String ecode,
			@RequestParam(value = "contains", required = false) Boolean contains,
			@RequestParam(value = "apikey", required = true) String apikey) {
		if (userRepo.findByApikey(apikey).getUserStatus().equals("USER")) {
			if (contains) {
				return proRepo.findProductsByKeepsEcodesCode(ecode);
			}

			if (!contains) {
				List<Product> allProducts = proRepo.findAll();
				proRepo.findProductsByKeepsEcodesCode(ecode).forEach(prod -> allProducts.remove(prod));
				return allProducts;

			}

		}
		return null;
	}

	// Find by Ecode contains or doesn't contain
	// then order by EAN, name or sugar
	// contains = 0 or 1
	@GetMapping(value = "/product", params = { "apikey", "ecode", "contains", "order" })
	public List<Product> findProductsByEcodeOrder(@RequestParam(value = "ecode", required = true) String ecode,
			@RequestParam(value = "contains", required = true) Boolean contains,
			@RequestParam(value = "order", required = true) String order,
			@RequestParam(value = "apikey", required = true) String apikey) {
		if (userRepo.findByApikey(apikey).getUserStatus().equals("USER")) {
			if (order != null && contains) {
				return proRepo.findProductsByKeepsEcodesCode(ecode, (Sort.by(Sort.Direction.ASC, order)));
			}
			if (order != null && !contains) {
				List<Product> allProductsNotContains = proRepo.findAll(Sort.by(Sort.Direction.ASC, order));
				proRepo.findProductsByKeepsEcodesCode(ecode).forEach(prod -> allProductsNotContains.remove(prod));
				return allProductsNotContains;
			}
			if (order == null) {
				return findProductsByEcode(ecode, contains, apikey);
			}
		}
		return null;
	}

	// Find by sugar amount
	@GetMapping(value = "/product", params = { "apikey", "sugar" })
	public List<Product> findProductsLessThanSugar(@RequestParam(value = "sugar", required = false) double amount,
			@RequestParam(value = "apikey", required = true) String apikey) {
		if (userRepo.findByApikey(apikey).getUserStatus().equals("USER")) {
			return proRepo.sugarIsLessThanEqualOrderBySugar(amount);
		}
		return null;
	}

}
