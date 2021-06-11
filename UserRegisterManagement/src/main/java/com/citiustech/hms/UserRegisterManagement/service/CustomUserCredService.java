package com.citiustech.hms.UserRegisterManagement.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.model.Login;

@Service
public class CustomUserCredService implements UserDetailsService{

	@Autowired
	LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Login login=loginService.loadCredentialsByUsername(email);
		return new User(login.getEmail(), login.getPassword(), new ArrayList<>());
		
	}
	

}
