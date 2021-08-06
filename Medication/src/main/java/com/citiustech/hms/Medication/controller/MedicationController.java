package com.citiustech.hms.Medication.controller;

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

import com.citiustech.hms.Medication.dto.MedicationDto;
import com.citiustech.hms.Medication.service.MedicationService;

@RestController
@RequestMapping("/savemedication")
public class MedicationController {
	@Autowired
	private MedicationService medicationService;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveMedication(@RequestBody List<MedicationDto> medicationDto,@RequestHeader("authorization") String authorization){
		return medicationService.saveMedication(medicationDto,authorization);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<MedicationDto>> getAllMedication(@RequestHeader("authorization") String authorization){
	List<MedicationDto> medications=medicationService.getAllMedicationByPhysian(authorization);
	return ResponseEntity.ok(medications);
	}
	
	
	
	@GetMapping("/patients")
	public ResponseEntity<List<Long>> getAssociatedPatientId(@RequestHeader("authorization") String authorization){
	List<Long> patientIdList=medicationService.getAllPatientByEmployee(authorization);
	if(patientIdList!=null) {
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<Long>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/patients/{patientId}")
	public ResponseEntity<List<MedicationDto>> getDiagnosisByPatient(@RequestHeader("authorization") String authorization,@PathVariable String patientId ){
	List<MedicationDto> patientIdList=medicationService.getMedicationByPatient(authorization,patientId);
	if(patientIdList!=null) {
	return new ResponseEntity<List<MedicationDto>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<MedicationDto>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	

}
