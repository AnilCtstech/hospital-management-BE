package com.citiustech.hms.Procedures.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Procedures.dto.ProcedureDto;
import com.citiustech.hms.Procedures.service.ProcedureService;

@RestController
@RequestMapping("/saveprocedure")
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveProcedure(@RequestBody List<ProcedureDto> procedureDto){
		return procedureService.saveProcedure(procedureDto);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<ProcedureDto>> getAllProcedure(@RequestHeader("authorization") String authorization){
	List<ProcedureDto> medications=procedureService.getAllProcedureByPhysian(authorization);
	return ResponseEntity.ok(medications);
	}
	
	
	
	@GetMapping("/patients")
	public ResponseEntity<List<Long>> getAssociatedPatientId(@RequestHeader("authorization") String authorization){
	List<Long> patientIdList=procedureService.getAllPatientByEmployee(authorization);
	if(patientIdList!=null) {
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	
}
