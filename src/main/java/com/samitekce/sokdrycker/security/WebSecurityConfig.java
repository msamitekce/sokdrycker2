package com.samitekce.sokdrycker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.samitekce.sokdrycker.web.UserDetailServiceImpl;

//import com.samitekce.sokdrycker.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/", "/register", "/reset", "/verify/**","/api/**").permitAll() 																					
				.and()
		.authorizeRequests().anyRequest().hasAnyAuthority("USER", "ADMIN")
				.and()
		.formLogin()
			.loginPage("/")
			.defaultSuccessUrl("/home", true)
				.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/loggedout");
	}

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
//		http.csrf().disable();
//	http.authorizeRequests().anyRequest().permitAll();		
}
//
//http.antMatcher("/match1/**")
//.authorizeRequests()
//  .antMatchers("/match1/user").hasRole("USER")
//  .antMatchers("/match1/spam").hasRole("SPAM")
//  .anyRequest().isAuthenticated();