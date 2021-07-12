package com.citiustech.hms.UserRegisterManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.dto.ChangePasswordDto;
import com.citiustech.hms.UserRegisterManagement.dto.ForgetPasswordDto;
import com.citiustech.hms.UserRegisterManagement.dto.Login;
import com.citiustech.hms.UserRegisterManagement.service.EmailService;
import com.citiustech.hms.UserRegisterManagement.service.LoginService;
import com.citiustech.hms.UserRegisterManagement.utils.JwtUtil;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private EmailService emailService;
	

	//change password
	@PutMapping("/change-password")
	public ResponseEntity<String> userLogin(@RequestBody ChangePasswordDto changePassdto,
											@RequestHeader("Authorization") String token) {
		
		String tokenStr=token.substring(7);
		String email=jwtUtil.extractUsername(tokenStr);
//		try {
//			
//			authManager.authenticate(
//					new UsernamePasswordAuthenticationToken(email, changePassdto.getOldPassword())
//					);
//		} catch (Exception e) {			
//			return ResponseEntity.status(401).body("Username/email invalid");
//					//unprocessableEntity().body("Username/email invalid");
//		}
		
		
		//System.out.println(changePassdto.getNewPassword()+" : "+ changePassdto.getOldPassword()+" : "+ tokenStr);

		String msg=loginService.updatePasswordByUsername(email, changePassdto.getNewPassword());
		
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/forget-password/{email}")
	public ResponseEntity<String> getPassword(@PathVariable String email){
		if(email != null) {
		String  userPassword = loginService.getUserPassword(email);
			if(userPassword != null) {
				emailService.sendEmailtoUser(email,userPassword);
			}
		}else {
			return new ResponseEntity<String>("email is missing", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("password sent to mail", HttpStatus.OK);
	}
	
}
