package com.hdilhara.shopme.jwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class config extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired 
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
         .antMatchers("/addAdmin").hasAuthority("admin")
         .antMatchers("/system/**").hasAuthority("admin")
         .antMatchers("/user/**").hasAnyAuthority("admin","user")
         .antMatchers("/addUser").permitAll()
         .antMatchers("/product/**").permitAll()
         .antMatchers("/shopme/**").permitAll()
         .antMatchers("/images/**").permitAll()
         .antMatchers("/authenticate").permitAll()
         .anyRequest().authenticated()
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	
	
}
