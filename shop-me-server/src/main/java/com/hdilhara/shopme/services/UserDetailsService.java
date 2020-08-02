package com.hdilhara.shopme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.entity.Orders;
import com.hdilhara.shopme.entity.UserDetails;
import com.hdilhara.shopme.repos.UserDetailsRepo;

@Service
public class UserDetailsService {

	@Autowired
	UserDetailsRepo userDetailsRepo;
	

	public boolean addDeliverDetails(UserDetails userDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			String userName=authentication.getName();
			if(userName.equals(userDetails.getUserName())) {
			userDetailsRepo.save(userDetails);
			return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}	
	}

	public Optional<UserDetails> getUserDetails(String userName) {
		 Optional<UserDetails> user=userDetailsRepo.findById(userName);
		 
//		System.out.println(user.get().getCity());
		return user;
//		return null;
	}

	public List<Orders> getOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserDetails> userDetails= userDetailsRepo.findById(authentication.getName());
		if(userDetails.isPresent())
			return userDetails.get().getOrder();
		else
			return null;
	}
	
	
}
