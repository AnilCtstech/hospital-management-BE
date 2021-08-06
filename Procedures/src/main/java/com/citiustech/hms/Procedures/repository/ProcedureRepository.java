package com.citiustech.hms.Procedures.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.citiustech.hms.Procedures.entity.Procedures;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedures, Long> {

	List<Procedures> findByPatientId(long id);

	Optional<List<Procedures>> findAllByCreatedBy(String user);

	@Query("select DISTINCT d.patientId from Procedures d where d.createdBy=?1")
	List<Long> findPatientIdByCreatedBy(String user);

	@Query("select  d from Procedures d where d.createdBy=?1 and d.patientId=?2")
	Optional<List<Procedures>> findAllByCreatedByAndPatient(String user, long parseLong);

}
