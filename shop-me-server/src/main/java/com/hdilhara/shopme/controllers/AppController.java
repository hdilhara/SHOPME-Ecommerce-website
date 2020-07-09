package com.hdilhara.shopme.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.shopme.jwt.db.UserDto;
import com.hdilhara.shopme.jwt.db.UserRepo;
import com.hdilhara.shopme.jwt.db.Users;



@RestController
public class AppController {
	
	@Autowired 
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/")
	public String homePage() {
		return "This is todo App ";
	}
	
	//My custom urls to add users to userdb
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
		try {
			Optional<Users> existUser=userRepo.findById(userDto.getUsername());
			if(existUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
			}
			Users user=new Users(1,userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),"user");
				userRepo.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER_NOT_CREATEDPlase supply valid values!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("User Created!");
	}
	@PostMapping("/addAdmin")
	public ResponseEntity<Object> addAdmin(@RequestBody UserDto userDto) {
		try {
			Optional<Users> existUser=userRepo.findById(userDto.getUsername());
			if(existUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
			}
			Users user=new Users(1,userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),"admin");
				userRepo.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER_NOT_CREATEDPlase supply valid values!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("User Created!");
	}
	
	
}
