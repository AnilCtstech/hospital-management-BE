package com.citiustech.hms.UserRegisterManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.repository.EmployeeRepository;
@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public ResponseEntity<Object> createEmployee(Employee employeeRequest) {
		Employee employee= new Employee();
		if (employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("the Employee is already exist, Failed to create new Employee");
		} else {
			employee.setTitle(employeeRequest.getTitle());
			employee.setFirstName(employeeRequest.getFirstName());
			employee.setLastName(employeeRequest.getLastName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setDateOfBirth(employeeRequest.getDateOfBirth());
			employee.setRole(employeeRequest.getRole());
			employee.setPassword(employeeRequest.getFirstName()+"@123");
			Employee savedEmployee = null;
			try {
				savedEmployee = employeeRepository.save(employee);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (employeeRepository.findById(savedEmployee.getEmployeeId()).isPresent())
				return ResponseEntity.ok("Employee Created successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed Creating Employee as specified");

		}
	}

}
