package com.citiustech.hms.Procedures.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Procedures.entity.ProcedureDatabase;

@Repository
public interface ProcedureDatabaseRepository extends JpaRepository<ProcedureDatabase, String>{

	@Query("select s.procedureCode from ProcedureDatabase s")
	List<String> findAllByProcedureCode();
	
	ProcedureDatabase findByProcedureCode(String procedureCode);

	@Query("select s.procedureDescription from ProcedureDatabase s")
	List<String> findAllByProcedureDescription();
	
	@Query("select Distinct s from ProcedureDatabase s where s.procedureDescription like %:keyword% ")
	List<ProcedureDatabase> findAllByProcedureDescriptionContaining(@Param ("keyword")    String key);

//	@Query("select s.procedureCode from ProcedureDatabase s where s.procedureCode like %:keyword% ")
//	List<String> findAllByProcedureCodeContaining(@Param ("keyword") String key);
	@Query("select s from ProcedureDatabase s where s.procedureCode like %:keyword% ")
	List<ProcedureDatabase> findAllByProcedureCodeContaining(@Param ("keyword") String key);
	

}
