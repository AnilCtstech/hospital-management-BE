package com.citiustech.hms.Diagnosis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDto;
import com.citiustech.hms.Diagnosis.service.DiagnosisService;

@RestController
@RequestMapping("/savediagnosis")
public class DiagnosisController {

	@Autowired
	private DiagnosisService diagnosisService;

	@PostMapping("/create")
	public ResponseEntity<String> saveDiagnosis(@RequestBody  List<DiagnosisDto> diagnosisDto,@RequestHeader("authorization") String authorization) {
		return diagnosisService.saveDiagnosis(diagnosisDto,authorization);

	}

	@GetMapping("/getall")
	public ResponseEntity<List<DiagnosisDto>> getAllDiagnosis(@RequestHeader("authorization") String authorization){
	List<DiagnosisDto> diagnosis=diagnosisService.getAllDiagnosisByPhysian(authorization);
	return ResponseEntity.ok(diagnosis);
	}

	@GetMapping("/patients")
	public ResponseEntity<List<Long>> getAssociatedPatientId(@RequestHeader("authorization") String authorization){
	List<Long> patientIdList=diagnosisService.getAllPatientByEmployee(authorization);
	if(patientIdList!=null) {
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/patients/{patientId}")
	public ResponseEntity<List<DiagnosisDto>> getDiagnosisByPatient(@RequestHeader("authorization") String authorization,@PathVariable String patientId ){
	List<DiagnosisDto> patientIdList=diagnosisService.getDiagnosisByPatient(authorization,patientId);
	if(patientIdList!=null) {
	return new ResponseEntity<List<DiagnosisDto>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<DiagnosisDto>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	
}
