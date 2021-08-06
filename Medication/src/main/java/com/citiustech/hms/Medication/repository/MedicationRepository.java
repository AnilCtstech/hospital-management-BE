package com.citiustech.hms.Medication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Medication.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
	List<Medication> findByPatientId(long id);
}
