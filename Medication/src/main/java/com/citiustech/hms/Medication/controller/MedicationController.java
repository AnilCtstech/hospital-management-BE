package com.citiustech.hms.Medication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Medication.dto.MedicationDto;
import com.citiustech.hms.Medication.entity.Medication;
import com.citiustech.hms.Medication.repository.MedicationRepository;
import com.citiustech.hms.Medication.service.MedicationService;

@RestController
@RequestMapping("/savemedication")
public class MedicationController {

	@Autowired
	private MedicationService medicationService;

	@Autowired
	private MedicationRepository medicationRepository;

	@PostMapping("/create")
	public ResponseEntity<String> saveMedication(@RequestBody List<MedicationDto> medicationDto) {
		return medicationService.saveMedication(medicationDto);
	}

	@GetMapping("/medication/{id}")
	public List<Medication> getMedications(@PathVariable long id) {
		return medicationRepository.findByPatientId(id);
	}

}
