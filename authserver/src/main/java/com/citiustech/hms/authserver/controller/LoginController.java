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

import com.citiustech.hms.authserver.dto.ExtractRequest;
import com.citiustech.hms.authserver.dto.Login;
import com.citiustech.hms.authserver.entity.Employee;
import com.citiustech.hms.authserver.entity.Patient;
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

	// login Patient
//	@PostMapping("/login")
//	public ResponseEntity<String> userLogin(@RequestBody Login login) {
//		return loginService.userLogin(login);
//	}

	// login with authentication
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody Login login) throws Exception {
		String token = "";
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		} catch (Exception e) {
			return new ResponseEntity<String>("INVALID USERNAME OR PASSWORD!", HttpStatus.BAD_REQUEST);
		}

		String role = null;
		boolean isUpadted = false;
		long Id = 0;
		Employee employee = loginService.getEmployeeDataByEmail(login.getEmail());
		if (employee != null) {
			role = employee.getRole().getShortName();
			Id = employee.getEmployeeId();
			int count = employee.getPassCount();
			if (count == 0) {
				isUpadted = false;
			} else {
				isUpadted = true;
			}
		} else {
			Patient patient = loginService.getPatientDataByEmail(login.getEmail());
			if (patient != null) {
				role = "P";
				Id = patient.getPatientId();
			}
		}

		// String token=jwtUtil.generateToken(login.getEmail());
		token = jwtUtil.generateToken(login.getEmail(), role, isUpadted, Id);
		return new ResponseEntity<String>(token, HttpStatus.OK);

	}

	@GetMapping("/authenticate")
	public String authenticateUser() {
		return "AUTHENTICATION SUCCESSFUL";
	}

	@PostMapping("/extract_claims")
	public String getClaimFromToken(@RequestBody ExtractRequest extaractRequest) throws Exception {
		Claims claims = jwtUtil.extractAllClaims(extaractRequest.getToken());
		String role = (String) claims.get("role");
		String Id = String.valueOf(claims.get("id"));
		String email = (String) claims.get("sub");
		Employee employee = loginService.getEmployeeDataByEmail(claims.getSubject());
		// return employee.getEmployeeId();
		return Id + "," + role;
	}

}