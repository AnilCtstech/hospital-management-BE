package com.citiustech.hms.UserRegisterManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.dto.AllergyDatabaseDetails;
import com.citiustech.hms.UserRegisterManagement.dto.AllergyDatabaseId;
import com.citiustech.hms.UserRegisterManagement.entity.AllergyDatabase;
import com.citiustech.hms.UserRegisterManagement.repository.AllergyDatabaseRepository;

@Service
public class AllergyDataBaseService {
	@Autowired

	private AllergyDatabaseRepository allergyDatabaseRepository;

	public List<AllergyDatabaseDetails> getAllergyDetailsById(String allergyId) {
		List<AllergyDatabaseDetails> allergyDetails = new ArrayList<>();
		
		List<AllergyDatabase> allergies = allergyDatabaseRepository.findByAllergyId(allergyId);
	
		for (AllergyDatabase allergy : allergies) {
			allergyDetails.add(new AllergyDatabaseDetails(allergy.getAllergyType(), allergy.getAllergyName(),
					allergy.getAllergenSource(), allergy.getIsoFormsOrPartialSequencesOfAllergen(),
					allergy.getAllerginCity()));
		
		}

		return allergyDetails;
	}

	public List<AllergyDatabaseId> getAllergyDetailsByType(String allergyType) {
		List<AllergyDatabaseId> allergyDetails = new ArrayList<>();

		List<AllergyDatabase> allergies = allergyDatabaseRepository.findByAllergyType(allergyType);

		for (AllergyDatabase allergy : allergies) {
			allergyDetails.add(new AllergyDatabaseId(allergy.getAllergyId(), allergy.getAllergyName()));
		}

		return allergyDetails;
	}

	public List<String> getAllAllergyType(){
		return allergyDatabaseRepository.findAllAllergyType();
	}

	public List<String> getAllAllergyId(String allergyName){
		return allergyDatabaseRepository.findAllByAllergyId(allergyName);
	}
}
