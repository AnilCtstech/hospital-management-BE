package com.citiustech.hms.Diagnosis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDatabaseDto;
import com.citiustech.hms.Diagnosis.dto.DiagnosisDto;
import com.citiustech.hms.Diagnosis.service.DiagnosisDatabaseService;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisDatabaseController {
	@Autowired
	private DiagnosisDatabaseService diagnosisService;
	
	@GetMapping("/getAllDiagnosisCode")
	public List<String> getAllDiagnosisCode(){
		return diagnosisService.getAllDiagnosisCode();
	}
	
	@GetMapping("/{diagnosisCode}")
	public String getDiagnosisDescription(@PathVariable String diagnosisCode) {
		return diagnosisService.getDiagnosisDescription(diagnosisCode);
	}
	
//	@GetMapping("/getAllDiagnosisDescription/{key}")
//	public List<String> getAllDiagnosisDescriptionByKey(@PathVariable String key) {
//		return diagnosisService.getDiagnosisDescriptionByKey(key);
//	}
//	
	@GetMapping("/getAllDiagnosisDescription/{key}")
	public List<DiagnosisDatabaseDto> getAllDiagnosisDescriptionByKey(@PathVariable String key) {
		return diagnosisService.getDiagnosisDescriptionByKey(key);
	}
	
	
	@GetMapping("/getAllDiagnosisCode/{key}")
	public List<DiagnosisDatabaseDto> getAllDiagnosisCodeByKey(@PathVariable String key) {
		return diagnosisService.getDiagnosisCodeByKey(key);
	}
	

}
