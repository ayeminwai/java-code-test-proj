package com.ayeminwai.auth.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OauthUserServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("ayeminwai testing username :"+ username);
		UserDetails userDetails = null;

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		userDetails = new org.springframework.security.core.userdetails.User(username, "$2a$10$SgPB7kVrJA5aIxMRVABsJOfUwfU8iV3RRITyPinsoWdC15S7GgkXm" , authorities);
		
		
		return userDetails;
	}
	
	/*
	 * public static void main(String[] args) { BCryptPasswordEncoder encoder = new
	 * BCryptPasswordEncoder();
	 * 
	 * System.out.println(encoder.encode("password"));
	 * 
	 * }
	 */

}
