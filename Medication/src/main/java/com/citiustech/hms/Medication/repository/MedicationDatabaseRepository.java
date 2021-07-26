package com.citiustech.hms.Medication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Medication.entity.MedicationDatabase;
@Repository
public interface MedicationDatabaseRepository extends JpaRepository<MedicationDatabase, String> {

	List<MedicationDatabase> findByDrugId(String drugId);
	List<MedicationDatabase> findByDrugName(String drugName);
	}
