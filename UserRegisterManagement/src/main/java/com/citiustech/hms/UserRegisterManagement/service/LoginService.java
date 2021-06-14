package com.citiustech.hms.UserRegisterManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.model.Login;
import com.citiustech.hms.UserRegisterManagement.repository.EmployeeRepository;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;
import com.citiustech.hms.UserRegisterManagement.utils.LoginStatus;

@Service
public class LoginService {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

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
		if (employeeRepository.findByEmail(email).isPresent()) {
			Employee employee = employeeRepository.findByEmail(email).get();
			return new Login(employee.getEmail(), employee.getPassword());
		} else if (patientRepository.findByEmail(email).isPresent()) {
			Patient patient = patientRepository.findByEmail(email).get();
			return new Login(patient.getEmail(), patient.getPassword());
		} else
			return null;
	}

}
