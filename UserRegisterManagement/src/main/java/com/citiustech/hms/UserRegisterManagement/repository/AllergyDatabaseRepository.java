package com.citiustech.hms.UserRegisterManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citiustech.hms.UserRegisterManagement.entity.AllergyDatabase;

public interface AllergyDatabaseRepository extends JpaRepository<AllergyDatabase, String> {

	List<AllergyDatabase> findByAllergyId(String allergyId);
	
	List<AllergyDatabase> findByAllergyType(String allergyType);

	@Query("select Distinct s.allergyType from AllergyDatabase s")
	List<String> findAllAllergyType();

@Query("select Distinct s.allergyId from AllergyDatabase s where allergyName=?1")
	List<String> findAllByAllergyId(String allergyName);
	
}
