package com.samitekce.sokdrycker.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.samitekce.sokdrycker.repository.EcodeRepository;
import com.samitekce.sokdrycker.repository.UserRepository;

@Controller
public class FrontProfileController {

	@Autowired
	private EcodeRepository ecodeRepo;

	@Autowired
	private UserRepository userRepo;

	 @GetMapping(value = { "/profile" })
	    public String profilePage(Principal principal, Model model) {
		model.addAttribute("loggedUser", userRepo.findByUsername(principal.getName()));
		return "profile";
	    }
	
}
