package com.citiustech.hms.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.authserver.dto.Login;
import com.citiustech.hms.authserver.entity.Employee;
import com.citiustech.hms.authserver.entity.Patient;
import com.citiustech.hms.authserver.entity.Role;
import com.citiustech.hms.authserver.repository.EmployeeRepository;
import com.citiustech.hms.authserver.repository.PatientRepository;

@Service
public class LoginService {
@Autowired
private PatientRepository patientRepository;

@Autowired
private EmployeeRepository employeeRepository;

@Autowired
private JwtUtil jwtUtil;

//public ResponseEntity<String> userLogin(Login login) {
//	if (patientRepository.findByEmail(login.getEmail()).isPresent()){
//	
//		Patient patient=patientRepository.findByEmail(login.getEmail()).get();
//		System.out.println(patient.getEmail());
//		if (patient.getPassword().equals(login.getPassword())) {
//			return ResponseEntity.ok(LoginStatus.LOGIN_SUCCESS.name());
//		}
//			
//		else
//			return ResponseEntity.unprocessableEntity().body(LoginStatus.INCORRECT_PASSWORD.name());
//
//	}
//	else 
//		return ResponseEntity.unprocessableEntity().body(LoginStatus.INCORRECT_EMAIL.name());
//	}


	public Login loadCredentialsByUsername(String email) {	
		
		if (employeeRepository.findByEmail(email).isPresent()){
			Employee employee=employeeRepository.findByEmail(email).get();
			return new Login(employee.getEmail(),employee.getPassword());		
		}
		else if(patientRepository.findByEmail(email).isPresent()){
			Patient patient=patientRepository.findByEmail(email).get();
			return new Login(patient.getEmail(),patient.getPassword());
		}else
			return new Login();
	}

	public String generateToken(String email) {
		String token="";
		
		if (employeeRepository.findByEmail(email).isPresent()){
			Employee employee=employeeRepository.findByEmail(email).get();
			token=jwtUtil.generateToken(email, employee.getEmployeeId(), employee.getRole().toString());
			return 	token;	
		}
		else if(patientRepository.findByEmail(email).isPresent()){
			Patient patient=patientRepository.findByEmail(email).get();
			token=jwtUtil.generateToken(email, patient.getPatientId(), Role.PATIENT.toString());
			return token;
		}else
			return token;
		
		
	}

}
