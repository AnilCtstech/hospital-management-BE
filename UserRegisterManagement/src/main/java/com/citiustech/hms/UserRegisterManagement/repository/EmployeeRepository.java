package com.citiustech.hms.UserRegisterManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.entity.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	@Modifying
	@Query("update Employee e set e.password=:newPassword ,e.passCount = 1  where e.email=:email")
	void updatePassword(@Param(value = "email") String email, @Param(value = "newPassword") String newPassword);

	Page<Employee> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName,
			Pageable pageable);

	Page<Employee> findByFirstNameIgnoreCaseContaining(String firstName, Pageable pageable);

	Optional<Employee> findByEmployeeId(Long id);

	Employee findByFirstNameAndLastName(String firstName, String lastName);

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByFirstNameContains(String name);

	List<Employee> findByFirstNameIgnoreCaseContaining(String firstName);
	
	List<Employee> findAllByRole(Role role);

	@Modifying
	@Query("update Employee e set e.isActive =:is_active , e.isBlocked =:is_blocked where e.email =:email")
	void updateEmployeeStatus(@Param(value = "email") String email, @Param(value = "is_active") boolean isActive,
			@Param(value = "is_blocked") boolean isBlocked);
}
