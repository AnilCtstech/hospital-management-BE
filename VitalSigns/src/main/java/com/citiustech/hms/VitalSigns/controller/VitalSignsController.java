package com.citiustech.hms.VitalSigns.controller;

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

import com.citiustech.hms.VitalSigns.dto.VitalSignsDto;
import com.citiustech.hms.VitalSigns.service.VitalSignsService;

@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

	@Autowired
	private VitalSignsService vitalSignsService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveVitalSigns(@RequestBody VitalSignsDto vitalSignsDto) {
		
		return vitalSignsService.saveVitalSigns(vitalSignsDto);
	}
	
	@GetMapping("/patients/{patientId}")
	public ResponseEntity<List<VitalSignsDto>> getDiagnosisByPatient(@RequestHeader("authorization") String authorization,@PathVariable String patientId ){
	List<VitalSignsDto> patientIdList=vitalSignsService.getVitalSignsByPatient(authorization,patientId);
	if(patientIdList!=null) {
	return new ResponseEntity<List<VitalSignsDto>>(patientIdList, HttpStatus.OK);

	 }
	return new ResponseEntity<List<VitalSignsDto>>(patientIdList, HttpStatus.NO_CONTENT);
	}
	
	
}
