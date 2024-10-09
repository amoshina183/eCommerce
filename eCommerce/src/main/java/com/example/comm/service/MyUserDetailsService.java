package com.example.comm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.comm.entity.User;
import com.example.comm.entity.UserPrincipal;
import com.example.comm.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
	}

}
