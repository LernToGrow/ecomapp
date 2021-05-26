package com.EcommerceApplications.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.EcommerceApplications.dao.UserRepository;
import com.EcommerceApplications.entites.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userrepository.getUserByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Could not found User!!  ");
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		return customUserDetails ;
	}

}
