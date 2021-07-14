package com.citiustech.hms.UserRegisterManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.dto.AllergyDatabaseDetails;
import com.citiustech.hms.UserRegisterManagement.dto.AllergyDatabaseId;
import com.citiustech.hms.UserRegisterManagement.service.AllergyDataBaseService;

@RestController
@RequestMapping("/allergy")
public class AllergyDatabaseController {
	@Autowired
	private AllergyDataBaseService allergyDatabaseService;

	@GetMapping("details/{allergyId}")
	public List<AllergyDatabaseDetails> getAllergyDetailsById(@PathVariable String allergyId) {
		String modifiedAllergyId = allergyId.replace("%20", " ");
		return allergyDatabaseService.getAllergyDetailsById(allergyId);
	}

	@GetMapping("{allergyType}")
	public List<AllergyDatabaseId> getAllergyDetailsByType(@PathVariable String allergyType) {
		return allergyDatabaseService.getAllergyDetailsByType(allergyType);
	}
	
	@GetMapping("/getAllAllergyTypes")
	public List<String> getAllAllergyTypes(){
		return allergyDatabaseService.getAllAllergyType();
	}

	@GetMapping("detail/{allergyName}")
	public List<String> getAllAllergyId(@PathVariable String allergyName){
		return allergyDatabaseService.getAllAllergyId(allergyName);
	}

}
