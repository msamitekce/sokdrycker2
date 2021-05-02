package com.samitekce.sokdrycker.web;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.samitekce.sokdrycker.domain.User;
import com.samitekce.sokdrycker.repository.UserRepository;

@Controller
public class FrontRegisterController {
	@Autowired
	private UserRepository userRepo;

	BCryptPasswordEncoder password_hasher = new BCryptPasswordEncoder();
	String serverName;
	// Register page
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		serverName = request.getServerName();
		model.addAttribute("user", new User());
		
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(User user) throws MessagingException {
		user.setPasswordHash(password_hasher.encode(user.getPasswordHash()));
		user.setRole("NOTVALID");
		user.setApikey();
		userRepo.save(user);
		sendEmail("msamitekce@gmail.com", user);
		return "redirect:/";
	}
	
	// Verify
	@GetMapping(value = "/verify/{apikey}")
	public String verify(@PathVariable("apikey") String apikey) {
		User user = userRepo.findUserByApikey(apikey);
		user.setRole("USER");
		userRepo.save(user);
		return "Success...";
	}

	@Value("${app.email.host}")
	private String SMTP_HOST_NAME;

	@Value("${app.email.user}")
	private String SMTP_AUTH_USER;

	@Value("${app.email.password}")
	private String SMTP_AUTH_PWD;

	@Value("${app.email.port}")
	private int SMTP_HOST_PORT;

	@Value("${app.email.from}") 
	private String FROM;

	public void sendEmail(String toMailAddress, User user) throws MessagingException {
		final String TO = toMailAddress; // {YOUR_RECIPIENT_EMAIL_ADDRESS}

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);

		Transport transport = mailSession.getTransport("smtp");

		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject("New Registery SokDrycker");
		message.setContent("Hello" + user.getUsername() + "you have registered to Sokdrycker <br>"
				+ "Click this link to verify your registery: <a href=\""+ serverName +"/verify/"+ user.getApikey() +"\">Verify</a>", "text/html");
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(FROM));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));

		transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
		System.out.println("email sent successfully........");

	}

}
