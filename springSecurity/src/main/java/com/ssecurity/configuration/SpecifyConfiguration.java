package com.ssecurity.configuration;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ssecurity.repository.IUserRepository;
import com.ssecurity.service.UserServiceImpl;
//acting as intercepter
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpecifyConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private IUserRepository repository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {

		return new UserServiceImpl(repository);
	}

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/secured/**").authenticated().anyRequest().permitAll()
			.and().formLogin()
				.permitAll();
	}*/	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		
		//.anyRequest().permitAll() 
		
		.antMatchers("/secured").hasRole("ADMIN")
	
		//.and().addFilterBefore(myFilter(), BasicAuthenticationFilter.class).httpBasic();
		
		//.anyRequest().hasAnyAuthority("VIEW").anyRequest().permitAll()
								.and().formLogin().permitAll();
	}

/*	@Bean
public MyFilter myFilter() {

		
	return new MyFilter();
}*/
	
}
