package com.citiustech.hms.Medication.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Medication.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

	List<Medication> findByPatientId(long id);

	Optional<List<Medication>> findAllByCreatedBy(String user);

	@Query("select DISTINCT d.patientId from Medication d where d.createdBy=?1")
	List<Long> findPatientIdByCreatedBy(String user);

	@Query("select  d from Medication d where d.createdBy=?1 and d.patientId=?2")
	Optional<List<Medication>> findAllByCreatedByAndPatient(String user, long parseLong);

}
