package com.citiustech.hms.VitalSigns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
