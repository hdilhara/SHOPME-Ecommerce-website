package com.hdilhara.shopme.jwt.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userdetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		final String authorizatioHeader=request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if(authorizatioHeader != null && authorizatioHeader.startsWith("Bearer ")) {
			jwt= authorizatioHeader.substring(7);
			username=jwtUtils.extractUsername(jwt);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails=this.userdetailsService.loadUserByUsername(username);
			
			if(jwtUtils.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	
	
	
}
