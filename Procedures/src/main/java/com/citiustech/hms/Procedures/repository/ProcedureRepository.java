package com.citiustech.hms.Procedures.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Procedures.entity.Procedures;
@Repository
public interface ProcedureRepository extends JpaRepository<Procedures, Long> {

}
