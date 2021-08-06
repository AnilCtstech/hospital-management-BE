package com.citiustech.hms.UserRegisterManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.dto.Profile;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.entity.Role;
import com.citiustech.hms.UserRegisterManagement.mapper.MapStructMapper;
import com.citiustech.hms.UserRegisterManagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private MapStructMapper mapStructMapper;

	public ResponseEntity<Object> createEmployee(Employee employeeRequest) {
		Employee employee = new Employee();
		if (employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("the Employee is already exist, Failed to create new Employee");
		} else {
			employee.setTitle(employeeRequest.getTitle());
			employee.setFirstName(employeeRequest.getFirstName());
			employee.setLastName(employeeRequest.getLastName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setDateOfBirth(employeeRequest.getDateOfBirth());
			employee.setRole(employeeRequest.getRole());
			employee.setPassword(employeeRequest.getFirstName() + "@123");
			employee.setIsActive(true);
			employee.setIsBlocked(false);
			employee.setIsAccess("Active");
			employee.setPassCount(0);
			Employee savedEmployee = null;

			try {
				savedEmployee = employeeRepository.save(employee);

				boolean isMailSent = emailService.sentEmail(employee.getPassword());
				if (isMailSent) {
					// return ResponseEntity.ok("mail send successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (employeeRepository.findById(savedEmployee.getEmployeeId()).isPresent())
				return ResponseEntity.ok("Employee Created successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed Creating Employee as specified");

		}
	}

	public List<Profile> findAllEmployeeByName(String employeeName) {

		String[] names = employeeName.trim().split("\\s+");
		String firstName = null, lastName;
		if (names.length > 1) {
			firstName = names[0];
			lastName = names[1];
		} else {
			firstName = names[0];
			lastName = names[0];
		}
		Pageable pageable = PageRequest.of(0, 10,
				Sort.by("firstName").ascending().and(Sort.by("lastName").descending()));

		Page<Employee> page = employeeRepository
				.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(firstName, lastName, pageable);

		List<Employee> employees = page.getContent();

		List<Profile> profiles = new ArrayList<>();

		employees.stream().forEach(e -> {
			profiles.add(mapStructMapper.employeeToProfile(e));
		});

		// mapStructMapper.employeeToProfile(null)

		return profiles;
	}

	public String getNameById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		String name = "";
		if (optional.isPresent()) {
			Employee employee = optional.get();
			name = employee.getFirstName() + " " + employee.getLastName();
		}
		return name;
	}

	public ResponseEntity<String> getEmployeeById(String id) {
		Optional<Employee> result = employeeRepository.findById(Long.parseLong(id));
		if (result.isPresent()) {
			String name = result.get().getFirstName() + " " + result.get().getLastName();
			return ResponseEntity.ok(name);
		}
		return null;
	}

	public List<Profile> findEmployeeByName(String employeeName) {
		String[] names = employeeName.trim().split("\\s+");
		String firstName = null, lastName;
		if (names.length > 1) {
			firstName = names[0];
			lastName = names[1];
		} else {
			firstName = names[0];
			lastName = "";
		}

		List<Employee> employees = employeeRepository.findByFirstName(firstName);
		List<Profile> profiles = new ArrayList<>();
		employees.stream().forEach(e -> {
			profiles.add(mapStructMapper.employeeToProfile(e));
		});
		return profiles;
	}

	public List<Long> getEmployeeIdByName(String name) {
		List<Employee> employeeList = employeeRepository.findByFirstNameIgnoreCaseContaining(name);
		List<Long> employeeIdList = new ArrayList<>();
		for (Employee patient : employeeList) {
			employeeIdList.add(patient.getEmployeeId());
		}
		return employeeIdList;
	}
	
	public List<Profile> findEmployeeByRole(Role role){
		List<Employee> employees = employeeRepository.findAllByRole(role);
		List<Profile> profiles = new ArrayList<>();
		for (Employee employee : employees) {
			Profile profile = new Profile();
			profile.setId(employee.getEmployeeId());
			profile.setFirstName(employee.getFirstName());
			profile.setLastName(employee.getLastName());
			profile.setEmail(employee.getEmail());
			profile.setDateOfBirth(employee.getDateOfBirth().toString());
			profile.setRole(employee.getRole());

			if (employee.getIsActive() == true) {
				profile.setStatus("Active");
			} else if (employee.getIsBlocked() == true) {
				profile.setStatus("Blocked");
			} else {
				profile.setStatus("Unknown");
			}

			profiles.add(profile);
		}
		return profiles;
	}
	
	public List<Profile> findAllEmployee(){
		List<Employee> employees = employeeRepository.findAll();
		List<Profile> profiles = new ArrayList<>();
		for (Employee employee : employees) {
			Profile profile = new Profile();
			profile.setId(employee.getEmployeeId());
			profile.setFirstName(employee.getFirstName());
			profile.setLastName(employee.getLastName());
			profile.setEmail(employee.getEmail());
			profile.setDateOfBirth(employee.getDateOfBirth().toString());
			profile.setRole(employee.getRole());

			if (employee.getIsActive() == true) {
				profile.setStatus("Active");
			} else if (employee.getIsBlocked() == true) {
				profile.setStatus("Blocked");
			} else {
				profile.setStatus("Unknown");
			}

			profiles.add(profile);
		}
		return profiles;
	}
	
	@Transactional
	public String updateEmployeeStatus(String email, Profile profile) {
		boolean isActive;
		boolean isBlocked;
		System.out.println("status : "+profile.getStatus());
		if (profile.getStatus().equalsIgnoreCase("Active")) {
			isActive = true;
			isBlocked = false;
		} else {
			isActive = false;
			isBlocked = true;
		}
		employeeRepository.updateEmployeeStatus(email, isActive, isBlocked);
		return "Status Updated";
	}


}
