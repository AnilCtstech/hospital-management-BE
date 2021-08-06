package com.citiustech.hms.VitalSigns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.VitalSigns.dto.VitalSignsDto;
import com.citiustech.hms.VitalSigns.entity.VitalSigns;
import com.citiustech.hms.VitalSigns.repository.VitalSignsRepository;
import com.citiustech.hms.VitalSigns.service.VitalSignsService;

@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

	@Autowired
	private VitalSignsService vitalSignsService;

	@Autowired
	private VitalSignsRepository vitalSignsRepository;

	@PostMapping("/save")
	public ResponseEntity<String> saveVitalSigns(@RequestBody VitalSignsDto vitalSignsDto) {

		return vitalSignsService.saveVitalSigns(vitalSignsDto);
	}

	@GetMapping("/vitalsigns/{id}")
	public List<VitalSigns> getVitalSignsById(@PathVariable long id) {
		return vitalSignsRepository.findByPatientId(id);
	}
}
