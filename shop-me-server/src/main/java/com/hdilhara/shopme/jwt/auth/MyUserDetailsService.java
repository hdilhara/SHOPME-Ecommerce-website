package com.hdilhara.shopme.jwt.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.jwt.db.Authorities;
import com.hdilhara.shopme.jwt.db.UserRepo;
import com.hdilhara.shopme.jwt.db.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> optUserDetails=userRepo.findById(username);	
		if(!optUserDetails.isPresent())
			throw new UsernameNotFoundException(username);
		
		List<GrantedAuthority> grantedAuthorities= new ArrayList<GrantedAuthority>();
		for (Authorities role : optUserDetails.get().getAuthorities()) {
		    grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return new User(username, optUserDetails.get().getPassword(), grantedAuthorities );
	}
	
	

}
