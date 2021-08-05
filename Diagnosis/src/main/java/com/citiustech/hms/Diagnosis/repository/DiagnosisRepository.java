package com.citiustech.hms.Diagnosis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Diagnosis.entity.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String> {
	Optional<List<Diagnosis>> findAllByCreatedBy(String user);

	 @Query("select DISTINCT d.patientId from Diagnosis d where d.createdBy=?1")
	List<Long> findPatientIdByCreatedBy(String user);
	
}
