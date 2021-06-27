package com.citiustech.hms.UserRegisterManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.dto.Profile;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Role;
import com.citiustech.hms.UserRegisterManagement.service.EmployeeService;

@RestController
@RequestMapping("/user")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employeeRequest) {
				if(employeeRequest.getTitle() == null 
				|| employeeRequest.getFirstName() == null
				|| employeeRequest.getLastName() == null 
				|| employeeRequest.getEmail() == null
				|| employeeRequest.getDateOfBirth() == null
				|| employeeRequest.getRole()== null
				) {
			return ResponseEntity.unprocessableEntity().body("Attributes cannot be Null ");
		}else if(employeeRequest.getRole()== Role.ADMIN) {
			return ResponseEntity.unprocessableEntity().body("Please select valid role");
		}else {
			return employeeService.createEmployee(employeeRequest);
		}
	}
	
	@PostMapping("/employee/name")
	public ResponseEntity<List<Profile>> getNameAndRole(@RequestBody String employeeName){
		
		List<Profile> profiles = employeeService.findAllEmployeeByName(employeeName);
		
		return ResponseEntity.ok(profiles);
		
	}
}
