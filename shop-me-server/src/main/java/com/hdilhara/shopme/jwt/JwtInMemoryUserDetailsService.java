package com.hdilhara.shopme.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hdilhara.shopme.jwt.db.UserRepo;
import com.hdilhara.shopme.jwt.db.Users;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

//  static {
//    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
//        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//  }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> users = (List<Users>) userRepo.findAll();
		List<JwtUserDetails> inMemoryUserList = new ArrayList<JwtUserDetails>();
		for (Users u : users) {
			inMemoryUserList.add(new JwtUserDetails(1L, u.getUsername(), u.getPassword(), u.getRole()));
		}

		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
