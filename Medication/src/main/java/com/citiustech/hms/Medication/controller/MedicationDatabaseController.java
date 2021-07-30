package com.citiustech.hms.Medication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Medication.dto.MedicationDto;
import com.citiustech.hms.Medication.dto.MedicationDatabaseDto;
import com.citiustech.hms.Medication.service.MedicationDatabaseService;

@RestController
@RequestMapping("/medication")
public class MedicationDatabaseController {
	@Autowired
	private MedicationDatabaseService medicationDatabaseService;
	
	@GetMapping("/{drugId}")
	public List<MedicationDatabaseDto> getDrugDetailsById(@PathVariable String drugId) {
		return medicationDatabaseService.getDrugDetailsById(drugId);
	}
	
	@GetMapping("findby/{drugName}")
	public List<MedicationDto> getDrugDetailsByName(@PathVariable String drugName) {
		return medicationDatabaseService.getDrugDetailsByName(drugName);
	}
	
//	@GetMapping("/drugName/{key}")
//	public List<String> getAllDrugNameByKey(@PathVariable String key) {
//		
//		return medicationDatabaseService.getAllDrugNameByKey(key);
//	}
	
	@GetMapping("/drugName/{key}")
	public List<MedicationDatabaseDto> getAllMedicationsByKey(@PathVariable String key) {
		return medicationDatabaseService.getAllMedicationsByKey(key);
	}
	
	@GetMapping("/drugId/{key}")
	public List<MedicationDatabaseDto> getAllMedicationsByDrugIdKey(@PathVariable String key) {
		return medicationDatabaseService.getAllMedicationsByDrugIdKey(key);
	}
	

}
