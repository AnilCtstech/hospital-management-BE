package com.citiustech.hms.inboxmanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.inboxmanagement.dto.Login;
import com.citiustech.hms.inboxmanagement.dto.Role;
import com.citiustech.hms.inboxmanagement.util.JwtUtil;

@Service
public class LoginService {

	@Autowired
	private JwtUtil jwtUtil;

	public Login loadCredentialsByUsername(String email) {
		//from urm
		return null;
	
	}


	
}
