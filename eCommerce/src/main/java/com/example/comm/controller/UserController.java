package com.example.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.comm.entity.Product;
import com.example.comm.entity.User;
import com.example.comm.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		user.setUserPassword(encoder.encode(user.getUserPassword()));
		return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user){
		System.out.println("login screen: " +user);
		return userService.verify(user);
	}

}
