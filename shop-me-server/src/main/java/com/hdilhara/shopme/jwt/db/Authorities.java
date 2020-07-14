package com.hdilhara.shopme.jwt.db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities implements GrantedAuthority {

	@Id
	private String authority;
	
	

	public Authorities() {
		super();
	}

	public Authorities(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
