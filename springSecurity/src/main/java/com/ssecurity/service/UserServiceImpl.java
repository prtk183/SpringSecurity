package com.ssecurity.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ssecurity.model.User;
import com.ssecurity.repository.IUserRepository;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public class UserServiceImpl implements UserDetailsService{

	
private  IUserRepository repository;
	
	public UserServiceImpl(IUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<User> optionalUser =  repository.findByuserName(username);
	  optionalUser
	  .orElseThrow(() -> new  UsernameNotFoundException("Username is not available"));
	  return optionalUser.map(user -> 
	  new org.springframework.security.core.userdetails.User(user.getUserName(), 
			  user.getPassword(), getAuthorities(user))).get();
	}


	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream().map(role-> 
			new SimpleGrantedAuthority("ROLE_" + role.getRole()))
		.collect(Collectors.toList());
	}
}
