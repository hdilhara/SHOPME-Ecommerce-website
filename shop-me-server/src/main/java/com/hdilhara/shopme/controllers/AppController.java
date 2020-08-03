package com.hdilhara.shopme.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.shopme.jwt.db.Authorities;
import com.hdilhara.shopme.jwt.db.AuthoritiesRepo;
import com.hdilhara.shopme.jwt.db.UserDto;
import com.hdilhara.shopme.jwt.db.UserRepo;
import com.hdilhara.shopme.jwt.db.Users;


@CrossOrigin
@RestController
public class AppController {
	
	@Autowired 
	UserRepo userRepo;
	@Autowired
	AuthoritiesRepo authoritiesRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/")
	public String homePage() {
		return "This is todo App ";
	}
	
	//My custom urls to add users to userdb
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
		Map<String,String> response=new HashMap<>();
		try {
			Optional<Users> existUser=userRepo.findById(userDto.getUsername());
			if(existUser.isPresent()) {
				response.put("msg", "User Already Exists");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			Users user=new Users(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
				List<Authorities> authrities=new ArrayList<Authorities>();
				authrities.add(authoritiesRepo.findById("user").get());
				user.setAuthorities(authrities);
//				user.addAuthorityUser();
				userRepo.save(user);
		} catch (Exception e) {
			response.put("msg", "USER_NOT_CREATED");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		response.put("msg","User Created!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	@PostMapping("/addAdmin")
	public ResponseEntity<Object> addAdmin(@RequestBody UserDto userDto) {
		try {
			Optional<Users> existUser=userRepo.findById(userDto.getUsername());
			if(existUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
			}
			Users user=new Users(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
				userRepo.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER_NOT_CREATEDPlase supply valid values!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("User Created!");
	}
	
	
}
