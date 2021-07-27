package com.citiustech.hms.Procedures.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Procedures.service.ProcedureDatabaseService;

@RestController
@RequestMapping("/procedure")
public class ProcedureDatabaseController {

	@Autowired
	private ProcedureDatabaseService procedureDatabaseService;

	@GetMapping("/getAllProcedureCode")
	public List<String> getAllProcedureCode() {
		return procedureDatabaseService.getAllProcedureCode();
	}

	@GetMapping("/{procedureCode}")
	public String getProcedureDescription(@PathVariable String procedureCode) {
		return procedureDatabaseService.getProcedureDescription(procedureCode);
	}
	
	@GetMapping("/getAllProcedureDescription")
	public List<String> getAllProcedureDescription() {
		return procedureDatabaseService.getAllProcedureDescription();
	}
	
	
	
	
	
}
