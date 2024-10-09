package com.example.comm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.comm.entity.Product;
import com.example.comm.entity.User;
import com.example.comm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;
	
	public User saveUser(User user){
		return userRepository.save(user);
	}

	public String verify(User user) {
		Authentication authentication = null;
		try {
			authentication = authManager
							.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUserName());
			//return "success";
		}
		
		return "fail";
	}

}
