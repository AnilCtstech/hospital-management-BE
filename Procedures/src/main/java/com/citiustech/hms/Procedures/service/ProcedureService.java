package com.citiustech.hms.Procedures.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Procedures.dto.ProcedureDto;
import com.citiustech.hms.Procedures.entity.Procedures;
import com.citiustech.hms.Procedures.repository.ProcedureRepository;

@Service
public class ProcedureService {

	@Autowired
	private ProcedureRepository procedureRepository;

	public ResponseEntity<String> saveProcedure(ProcedureDto procedureDto) {
		Procedures temp= new Procedures();
		temp.setProcedureCode(procedureDto.getProcedureCode());
		temp.setProcedureDescription(procedureDto.getProcedureDescription());
		temp.setProcedureIsdeprecated(procedureDto.isProcedureIsdeprecated());
		temp.setCreatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedBy(procedureDto.getUpdatedBy());
		temp.setCreatedBy(procedureDto.getCreatedBy());
		temp.setPatientId(procedureDto.getPatientId());
		temp.setEmployeeId(procedureDto.getEmployeeId());
		procedureRepository.save(temp);
		return ResponseEntity.ok("Procedure saved successfully");
	}
	
}
