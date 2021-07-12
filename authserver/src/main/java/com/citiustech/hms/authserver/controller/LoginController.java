package com.citiustech.hms.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.authserver.dto.Login;
import com.citiustech.hms.authserver.service.JwtUtil;
import com.citiustech.hms.authserver.service.LoginService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;
		
	//login Patient
//	@PostMapping("/login")
//	public ResponseEntity<String> userLogin(@RequestBody Login login) {
//		return loginService.userLogin(login);
//	}
	
	//login with authentication
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody Login login)  {
		String token="";
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
					);
		} catch (Exception e) {		
			return new ResponseEntity<String>("INVALID USERNAME OR PASSWORD!",HttpStatus.BAD_REQUEST);
		}
		
		token=loginService.generateToken(login.getEmail());
		return new ResponseEntity<String>(token,HttpStatus.BAD_REQUEST);
		
	}

	@GetMapping("/authenticate")
	public String authenticateUser(){
		return "AUTHENTICATION SUCCESSFUL";
	}

}