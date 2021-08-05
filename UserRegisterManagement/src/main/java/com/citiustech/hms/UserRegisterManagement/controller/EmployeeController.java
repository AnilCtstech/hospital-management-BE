package com.citiustech.hms.UserRegisterManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		if (employeeRequest.getTitle() == null || employeeRequest.getFirstName() == null
				|| employeeRequest.getLastName() == null || employeeRequest.getEmail() == null
				|| employeeRequest.getDateOfBirth() == null || employeeRequest.getRole() == null) {
			return ResponseEntity.unprocessableEntity().body("Attributes cannot be Null ");
		} else if (employeeRequest.getRole() == Role.ADMIN) {
			return ResponseEntity.unprocessableEntity().body("Please select valid role");
		} else {
			return employeeService.createEmployee(employeeRequest);
		}
	}

	@PostMapping("/employee/name")
	public ResponseEntity<List<Profile>> getNameAndRole(@RequestBody String employeeName) {

		List<Profile> profiles = employeeService.findAllEmployeeByName(employeeName);
		return ResponseEntity.ok(profiles);

	}

	@GetMapping("/employee/name/{id}")
	public ResponseEntity<String> getEmployeeNameById(@PathVariable("id") String id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<String> getNameOnly(@PathVariable Long id) {
		String name = employeeService.getNameById(id);
		return ResponseEntity.ok(name);
	}

	@PostMapping("/employee/ename")
	public ResponseEntity<List<Profile>> getEmployeeByName(@RequestBody String employeeName) {
		List<Profile> profile = employeeService.findEmployeeByName(employeeName);
		return ResponseEntity.ok(profile);
	}

	// here working
	@GetMapping("/physiciansearch/{name}")
	public List<Long> getPatientIdByName(@PathVariable("name") String name) {
		return employeeService.getEmployeeIdByName(name);
	}
	
	@PostMapping("/employee/role")
	public ResponseEntity<List<Profile>> getAllEmployeeByRole(@RequestBody String role){
		List<Profile> profile = null;
		if(role.equals("DOCTOR"))
			profile = employeeService.findEmployeeByRole(Role.DOCTOR);
		else if(role.equals("NURSE"))
			profile = employeeService.findEmployeeByRole(Role.NURSE);
		else if(role.equals("ADMIN"))
			profile = employeeService.findEmployeeByRole(Role.ADMIN);
		else if(role.equals("PATIENT"))
			profile = employeeService.findEmployeeByRole(Role.PATIENT);
		
		return ResponseEntity.ok(profile);
	}
	
	@GetMapping("/employee/all")
	public ResponseEntity<List<Profile>> getAllEmployee(){		
		List<Profile> profile = employeeService.findEmployeeByRole(Role.DOCTOR);
		List<Profile> profile1 = employeeService.findEmployeeByRole(Role.NURSE);
		profile.addAll(profile1);		
		
		return ResponseEntity.ok(profile);
	}
	
	@PutMapping("/employee/update")
	public ResponseEntity<String> updateStatus(@RequestBody Profile profile) {
		String email = profile.getEmail();
		String msg = employeeService.updateEmployeeStatus(email, profile);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
}
