package com.samitekce.sokdrycker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samitekce.sokdrycker.domain.Product;
import com.samitekce.sokdrycker.repository.ProductRepository;

@Controller
public class FrontSearchController {

	@Autowired
	private ProductRepository productRepo;

	// Login page
	@GetMapping("/")
	public String login() {
		return "login";
	}

	// Home page
	@GetMapping(value = { "/home" })
	public String homePage() {
		return "home";
	}

	// Search pages
	// Search main page
	@GetMapping(value = { "/search" })
	public String searchPage() {
		return "search";
	}

	// Search by EAN
	@GetMapping(value = { "/search/ean-search" })
	public String eanSearchPage(@RequestParam(value = "ean", required = false) String ean, Model model) {
		model.addAttribute("search", productRepo.findByEan(ean));
		return "ean-search";
	}

	// Search by name
	@GetMapping(value = { "/search/name-search" })
	public String nameSearchPage(@RequestParam(value = "name", required = false) String name, Model model) {

		try {
			model.addAttribute("search", productRepo.findByNameIsContaining(name));
			return "name-search";

		} catch (Exception e) {
			return "name-search";
		}

	}

	// Search by sugar (less then or equal)
	@GetMapping(value = { "/search/sugar-search" })
	public String sugarSearchPage(@RequestParam(value = "sugar", required = false) String sugar, Model model) {
		try {
			double doubleSugar = Double.valueOf(sugar);
			model.addAttribute("search", productRepo.sugarIsLessThanEqualOrderBySugar(doubleSugar));
			return "sugar-search";

		} catch (Exception e) {
			return "sugar-search";
		}
	}

	// Search by Ecode
	@GetMapping(value = { "/search/ecode-search" })
	public String ecodeSearchPage(@RequestParam(value = "ecode", required = false) String ecode,
			@RequestParam(value = "contains", required = false) String contains, Model model) {
		try {

			if (contains.equals("1")) {
				model.addAttribute("search", productRepo.findProductsByKeepsEcodesCode(ecode));
				return "ecode-search";
			}
			if (contains.equals("0")) {
				List<Product> allProducts = productRepo.findAll();
				productRepo.findProductsByKeepsEcodesCode(ecode).forEach(prod -> allProducts.remove(prod));
				model.addAttribute("search", allProducts);
				return "ecode-search";
			}
		} catch (Exception e) {
			return "ecode-search";
		}
		return "ecode-search";
	}
}
