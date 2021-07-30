package com.citiustech.hms.Medication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Medication.entity.MedicationDatabase;
@Repository
public interface MedicationDatabaseRepository extends JpaRepository<MedicationDatabase, String> {

	List<MedicationDatabase> findByDrugId(String drugId);
	List<MedicationDatabase> findByDrugName(String drugName);
	
	@Query("select Distinct s.drugName from MedicationDatabase s where s.drugName like %:keyword% ")
	List<String> findAllDrugNameContaining(@Param ("keyword")  String key);
	
	
	@Query("select s from MedicationDatabase s where s.drugName like %:keyword% ")
	List<MedicationDatabase> findAllMedicationsContainingDrugName(@Param ("keyword") String key);
	

	@Query("select s from MedicationDatabase s where s.drugId like %:keyword% ")
	List<MedicationDatabase> findAllMedicationsContainingDrugId(@Param ("keyword") String key);
	
	
	}
