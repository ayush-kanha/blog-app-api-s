package com.blog.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	public UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from username i.e email from database
		
		User user = userRepo.findByEmail(username).orElseThrow(()->
									new ResourceNotFoundException("Üser", "email", username));;
		
		return user;
	}

}
