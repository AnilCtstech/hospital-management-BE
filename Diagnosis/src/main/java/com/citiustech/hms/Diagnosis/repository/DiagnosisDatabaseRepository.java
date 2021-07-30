package com.citiustech.hms.Diagnosis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Diagnosis.entity.DiagnosisDatabase;

@Repository
public interface DiagnosisDatabaseRepository extends JpaRepository<DiagnosisDatabase, String> {
	@Query("select s.diagnosisCode from DiagnosisDatabase s")
	List<String> findAllByDiagnosisCode();
	
	DiagnosisDatabase findByDiagnosisCode(String diagnosisCode);
	
	
	@Query("select s from DiagnosisDatabase s where s.diagnosisDescription like %:keyword% ")
	List<DiagnosisDatabase> findAllByDiagnosisDescriptionContaining(@Param ("keyword") String key);
	
	
	@Query("select s from DiagnosisDatabase s where s.diagnosisCode like %:keyword% ")
	List<DiagnosisDatabase> findAllByDiagnosisCodeContaining(@Param ("keyword") String key);
}
