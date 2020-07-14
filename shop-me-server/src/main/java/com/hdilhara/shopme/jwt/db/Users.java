package com.hdilhara.shopme.jwt.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="users")
public class Users {

	@Id
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_authorities",
			joinColumns = @JoinColumn(name="username"),
			inverseJoinColumns = @JoinColumn(name="authority")
			)
	private List<Authorities> authorities;
	
	
	//custom method
	public void addAuthorityAdmin() {
		if(authorities==null) {
			authorities=new ArrayList<Authorities>();
		}
		authorities.add(new Authorities("admin"));
	}
	public void addAuthorityUser() {
		if(authorities==null) {
			authorities=new ArrayList<Authorities>();
		}
		authorities.add(new Authorities("user"));
	}
	
	
	public Users() {
		super();
	}

	
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	
	
	
	
	
}
