package com.citiustech.hms.Diagnosis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<String> saveDiagnosis(@RequestBody DiagnosisDto diagnosisDto){
		return diagnosisService.saveDiagnosis(diagnosisDto);
		
	}

}
