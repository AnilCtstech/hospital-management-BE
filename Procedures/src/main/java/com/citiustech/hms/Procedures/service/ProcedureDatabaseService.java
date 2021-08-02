package com.citiustech.hms.Procedures.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Procedures.dto.ProcedureDatabaseDto;
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

	
	

	
	
	
	
	public List<ProcedureDatabaseDto> getAllProcedureDescriptionByKey(String key) {
		
       List<ProcedureDatabase> result= procedureDatabaseRepository.findAllByProcedureDescriptionContaining(key);
       List<ProcedureDatabaseDto> procedures= new ArrayList<ProcedureDatabaseDto>();
       for(ProcedureDatabase proc:result) {
    	   procedures.add(new ProcedureDatabaseDto(proc.getProcedureCode(), proc.getProcedureDescription()));
       }
       return procedures;
	}

//	public List<String> getAllProcedureCodeKey(String key) {
//		
//		 return procedureDatabaseRepository.findAllByProcedureCodeContaining(key);
//	}
	
	
	public List<ProcedureDatabaseDto> getAllProcedureCodeKey(String key) {
		
		  List<ProcedureDatabase> result= procedureDatabaseRepository.findAllByProcedureCodeContaining(key);
		  List<ProcedureDatabaseDto> procedures= new ArrayList<ProcedureDatabaseDto>();
	       for(ProcedureDatabase proc:result) {
	    	   procedures.add(new ProcedureDatabaseDto(proc.getProcedureCode(), proc.getProcedureDescription()));
	       }
	       return procedures;
	}
	
}
