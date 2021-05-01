package com.samitekce.sokdrycker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import com.samitekce.sokdrycker.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/register").permitAll(); // Enable css when
		http.authorizeRequests().anyRequest().permitAll();																							// logged out
//	.antMatchers("/api/").permitAll()
//		.and()
//	.authorizeRequests().anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/").defaultSuccessUrl("/home", true).permitAll()
//	.and()
//		.logout().permitAll().logoutUrl("/signout");
//    }

//    @Autowired
//    private UserDetailServiceImpl userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }
		http.csrf().disable();
	}
	

}