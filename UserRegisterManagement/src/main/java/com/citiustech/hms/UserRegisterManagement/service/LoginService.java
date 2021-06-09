package com.citiustech.hms.UserRegisterManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.model.Login;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;
import com.citiustech.hms.UserRegisterManagement.utils.LoginStatus;

@Service
public class LoginService {
@Autowired
private PatientRepository patientRepository;
public LoginStatus userLogin(Login login) {
	if (patientRepository.findByEmail(login.getEmail()).isPresent()){
	
		Patient patient=patientRepository.findByEmail(login.getEmail()).get();
		System.out.println(patient.getEmail());
		if (patient.getPassword().equals(login.getPassword())) {
			return LoginStatus.LOGIN_SUCCESS;
		}
			
		else
			return LoginStatus.INCORRECT_PASSWORD;
	}
	else 
		return LoginStatus.INCORRECT_EMAIL;
	}
}
