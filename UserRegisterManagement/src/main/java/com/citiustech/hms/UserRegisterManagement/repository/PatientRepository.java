package com.citiustech.hms.UserRegisterManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByPatientId(Long id);

	Optional<Patient> findByEmail(String email);

	List<Patient> findPatientByEmail(String email);

	List<Patient> findByOrderByFirstNameAsc();

	@Modifying
	@Query("update Patient p set p.isActive =:is_active , p.isBlocked =:is_blocked where p.email =:email")
	void updatePatientStatus(@Param(value = "email") String email, @Param(value = "is_active") boolean isActive,
			@Param(value = "is_blocked") boolean isBlocked);

	List<Patient> findByFirstNameContains(String name);

	List<Patient> findByFirstNameIgnoreCaseContaining(String firstName);

	// List<Patient>
	// findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingIn(String
	// firstName);

}
