package com.hdilhara.shopme.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.shopme.entity.UserDetails;
import com.hdilhara.shopme.services.UserDetailsService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;
	
	Map<String, String> responseMap = new HashMap();
	
	@PostMapping("/add/delivery-details")
	public ResponseEntity addDeliveryDetails(@RequestBody UserDetails userDetails) {
		if(userDetailsService.addDeliverDetails(userDetails)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseMap.put("msg","DELIVER_DETAILS_ADDED_SUCCESSFULLY"));
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseMap.put("msg","DELIVERDETAILS_CREATED_FAILED"));
	}
	
	@GetMapping("/get/delivery-details/{userName}")
	public ResponseEntity getUserDetails(@PathVariable String userName) {
		Optional<UserDetails> userDetails = userDetailsService.getUserDetails(userName);
		System.out.println(userDetails.isPresent());
		if(userDetails.isPresent())
		{
			return ResponseEntity.ok(userDetails.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap.put("msg", "USERNAME_IS_NOT_EXISTS"));
		}
	}
	
	
}
