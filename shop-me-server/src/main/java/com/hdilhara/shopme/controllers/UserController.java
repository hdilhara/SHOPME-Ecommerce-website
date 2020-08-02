package com.hdilhara.shopme.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

import com.hdilhara.shopme.dto.OrderDto;
import com.hdilhara.shopme.entity.Orders;
import com.hdilhara.shopme.entity.UserDetails;
import com.hdilhara.shopme.services.OrderService;
import com.hdilhara.shopme.services.UserDetailsService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	OrderService orderService;

	
	
	@PostMapping("/add/delivery-details")
	public ResponseEntity addDeliveryDetails(@RequestBody UserDetails userDetails) {
		Map<String, String> responseMap = new HashMap();
		if(userDetailsService.addDeliverDetails(userDetails)) {
			responseMap.put("msg","DELIVER_DETAILS_ADDED_SUCCESSFULLY");
			return  ResponseEntity.status(HttpStatus.ACCEPTED).body(responseMap);
		}
		responseMap.put("msg","DELIVERDETAILS_CREATED_FAILED");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseMap);
	}
	
	@GetMapping("/get/delivery-details/{userName}")
	public ResponseEntity getUserDetails(@PathVariable String userName) {
		Map<String, String> responseMap = new HashMap();
		Optional<UserDetails> userDetails = userDetailsService.getUserDetails(userName);
		if(userDetails.isPresent())
		{
			return ResponseEntity.ok(userDetails.get());
		}
		else {
			responseMap.put("msg", "USERNAME_IS_NOT_EXISTS");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
		}
	}
	
	@PostMapping("place/order")
	public Map<String, String> orderCreate(@RequestBody OrderDto orderDto) {
		Map<String, String> responseMap = new HashMap();
		System.out.println(orderDto);
		orderService.placeOrder(orderDto);
		 responseMap.put("msg", "ORDER_CREATED_SUCCESSFULLY");
		 return responseMap;
	}
	
	@GetMapping("get/orders")
	public List<Orders> getUserOrders(){
		return userDetailsService.getOrder();
	}
	
}
