package com.citiustech.hms.UserRegisterManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.model.Login;
import com.citiustech.hms.UserRegisterManagement.service.LoginService;
import com.citiustech.hms.UserRegisterManagement.utils.JwtUtil;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	//login Patient
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Login login) {
		return loginService.userLogin(login);
	}
	
	//login with authentication
	@PostMapping("/authenticate")
	public  ResponseEntity<String> generateToken(@RequestBody Login login)  {
		
		//System.out.println(authRequest);
		try {
			
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
					);
		} catch (Exception e) {			
			return ResponseEntity.status(401).body("Username/email invalid");
					//unprocessableEntity().body("Username/email invalid");
		}
		
		String token=jwtUtil.generateToken(login.getEmail());
		return ResponseEntity.ok(token);
		
	}
	
}
