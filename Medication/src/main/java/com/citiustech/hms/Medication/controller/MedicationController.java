package com.citiustech.hms.Medication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<String> saveMedication(@RequestBody MedicationDto medicationDto){
		return medicationService.saveMedication(medicationDto);
	}

}
