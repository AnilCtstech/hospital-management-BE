package com.citiustech.hms.UserRegisterManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citiustech.hms.UserRegisterManagement.dto.Profile;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByEmail(String email);

	@Modifying
	@Query("update Employee e set e.password=:newPassword ,e.passCount = 1  where e.email=:email")
	void updatePassword(@Param(value = "email") String email,@Param(value = "newPassword") String newPassword);

	
	Page<Employee> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName,String lastName,Pageable pageable);

	Page<Employee> findByFirstNameIgnoreCaseContaining(String firstName,Pageable pageable);

	
}
