package com.citiustech.hms.VitalSigns.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.VitalSigns.entity.VitalSigns;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Long> {

	List<VitalSigns> findByPatientId(long id);

	@Query("select  d from VitalSigns d where d.createdBy=?1 and d.patientId=?2")
	Optional<List<VitalSigns>> findAllByCreatedByAndPatient(String user, long parseLong);

}
