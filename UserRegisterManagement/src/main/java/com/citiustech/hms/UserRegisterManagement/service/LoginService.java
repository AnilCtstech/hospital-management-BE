package com.citiustech.hms.UserRegisterManagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.dto.Login;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.entity.Role;
import com.citiustech.hms.UserRegisterManagement.repository.EmployeeRepository;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;
import com.citiustech.hms.UserRegisterManagement.utils.JwtUtil;

@Service
public class LoginService {
@Autowired
private PatientRepository patientRepository;

@Autowired
private EmployeeRepository employeeRepository;

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
	
	@Transactional
	public String updatePasswordByUsername(String email,String newPassword) {

			employeeRepository.updatePassword(email,newPassword);
			return "Password updated!";	
	
	}

	public String getUserPassword(String email) {
		String password;
		if(employeeRepository.findByEmail(email).isPresent()) {
			Employee employee = employeeRepository.findByEmail(email).get();
			password = employee.getPassword();
		}else if(patientRepository.findByEmail(email).isPresent()) {
			Patient patient=patientRepository.findByEmail(email).get();
			password = patient.getPassword();
		}else {
			password = null;
		}
		return password;
	}


	

}
