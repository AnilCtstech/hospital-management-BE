package com.citiustech.hms.Procedures.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Procedures.entity.ProcedureDatabase;
import com.citiustech.hms.Procedures.repository.ProcedureDatabaseRepository;

@Service
public class ProcedureDatabaseService {

	@Autowired
	private ProcedureDatabaseRepository procedureDatabaseRepository;
	
	public List<String> getAllProcedureCode() {
		return procedureDatabaseRepository.findAllByProcedureCode();
	}

	public String getProcedureDescription(String procedureCode) {
	
	ProcedureDatabase procedureDatabase= procedureDatabaseRepository.findByProcedureCode(procedureCode) ;
		return procedureDatabase.getProcedureDescription();
	}

	public List<String> getAllProcedureDescription() {
			return procedureDatabaseRepository.findAllByProcedureDescription();
	}

}
