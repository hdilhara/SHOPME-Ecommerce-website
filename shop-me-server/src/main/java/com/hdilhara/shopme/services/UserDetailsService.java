package com.hdilhara.shopme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.entity.UserDetails;
import com.hdilhara.shopme.repos.UserDetailsRepo;

@Service
public class UserDetailsService {

	@Autowired
	UserDetailsRepo userDetailsRepo;

	public boolean addDeliverDetails(UserDetails userDetails) {
		try {
			userDetailsRepo.save(userDetails);
			return true;
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
	
	
}
