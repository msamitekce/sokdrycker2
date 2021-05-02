package com.samitekce.sokdrycker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.UserRepository;

@Controller
public class FrontRegisterController {
	@Autowired
	private UserRepository userRepo;

	BCryptPasswordEncoder password_hasher = new BCryptPasswordEncoder();

	// Register page
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}


	@PostMapping("/register")
	public String registerPost(User user) {
		user.setPasswordHash(password_hasher.encode(user.getPasswordHash()));
		user.setRole("NOTVALID");
		user.setApikey();
		userRepo.save(user);
		return "redirect:/";
	}
}
