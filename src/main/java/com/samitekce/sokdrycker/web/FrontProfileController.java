package com.samitekce.sokdrycker.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.UserRepository;

@Controller
public class FrontProfileController {

	BCryptPasswordEncoder password_hasher = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepo;

	// Profile page
	@GetMapping(value = { "/profile" })
	public String profilePage(Principal principal, Model model) {
		model.addAttribute("loggedUser", userRepo.findByUsername(principal.getName()));
		return "profile";
	}

	// Edit Email page
	@GetMapping(value = { "/profile/edit-email" })
	public String profileNewEmailPage(Principal principal, Model model) {
		User gottenUser = userRepo.findByUsername(principal.getName());
		model.addAttribute("user", gottenUser);
		return "profile_edit";
	}

	// Save email change
	@PostMapping(value = { "/profile/edit-email" })
	public String profileSaveEmail(@RequestParam(value = "email") String email, Principal principal) {
		User gottenUser = userRepo.findByUsername(principal.getName());
		gottenUser.setEmail(email);
		userRepo.save(gottenUser);
		return "redirect:/profile";
	}

	// New API key
	@RequestMapping(value = { "/profile/apikey" })
	public String profileApiKey(Principal principal) {
		User gottenUser = userRepo.findByUsername(principal.getName());
		gottenUser.setApikey();
		userRepo.save(gottenUser);
		System.out.println(gottenUser);
		return "redirect:/profile";
	}

	// Change password page
	@GetMapping(value = { "/profile/change-password" })
	public String changePasswordPage(Principal principal, Model model) {
		User gottenUser = userRepo.findByUsername(principal.getName());
		model.addAttribute("user", gottenUser);
		return "change-password";
	}

	// Save email change
	@PostMapping(value = { "/profile/change-password" })
	public String changePasswordSave(@RequestParam(value = "old-password") String old_password,
			@RequestParam(value = "new-password") String new_password,
			@RequestParam(value = "new-password-again") String new_password_again, Principal principal) {
		User gottenUser = userRepo.findByUsername(principal.getName());
		if (password_hasher.matches(old_password, gottenUser.getPasswordHash())) {
			if (new_password.equals(new_password_again)) {
				gottenUser.setPasswordHash(password_hasher.encode(new_password));
				userRepo.save(gottenUser);
				return "redirect:/profile";
			}
		}

		return "redirect:/profile";
	}

	@GetMapping(value = "/loggedout")
	public String signoutAction() {
		return "loggedout";
	}

}
