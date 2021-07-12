package com.citiustech.hms.UserRegisterManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
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
	AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private EmailService emailService;
	
	//login Patient
//	@PostMapping("/login")
//	public ResponseEntity<String> userLogin(@RequestBody Login login) {
//		return loginService.userLogin(login);
//	}
	
	//login with authentication
	@PostMapping("/authenticate")
	public  ResponseEntity<String> generateToken(@RequestBody Login login) throws Exception  {
		
		
		try {
			
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
					);
		} catch (Exception e) {			
			return ResponseEntity.status(401).body("Username/email invalid");
					//unprocessableEntity().body("Username/email invalid");
		}
		
		String role = null;
		boolean isUpadted = false;
		long Id = 0;
		Employee employee = loginService.getEmployeeDataByEmail(login.getEmail());
		if(employee != null) {
			role = employee.getRole().getShortName();
			Id = employee.getEmployeeId();
			int count = employee.getPassCount();
			if(count == 0) {
				isUpadted = false;
			}else {
				isUpadted = true;
			}
		}else {
			Patient patient = loginService.getPatientDataByEmail(login.getEmail());
			if(patient != null) {
				role = "P";
				Id = patient.getPatientId();
			}
		}
		
		//String token=jwtUtil.generateToken(login.getEmail());
		String token = jwtUtil.generateToken(login.getEmail(), role, isUpadted,Id);
		return ResponseEntity.ok(token);
		
	}
	//change password
	@PutMapping("/change-password")
	public ResponseEntity<String> userLogin(@RequestBody ChangePasswordDto changePassdto,
											@RequestHeader("Authorization") String token) {
		
		if(changePassdto.getOldPassword() == null || changePassdto.getOldPassword().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(changePassdto.getNewPassword() == null || changePassdto.getNewPassword().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(changePassdto.getConfirmPassword() == null || changePassdto.getConfirmPassword().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String tokenStr=token.substring(7);
		String email=jwtUtil.extractUsername(tokenStr);
		String msg=loginService.updatePasswordByUsername(email, changePassdto.getNewPassword());
		return new ResponseEntity<String>(msg, HttpStatus.OK);
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
