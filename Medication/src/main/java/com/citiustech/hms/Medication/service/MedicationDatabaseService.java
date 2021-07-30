package com.citiustech.hms.Medication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Medication.dto.MedicationDto;
import com.citiustech.hms.Medication.dto.MedicationDatabaseDto;
import com.citiustech.hms.Medication.entity.MedicationDatabase;
import com.citiustech.hms.Medication.repository.MedicationDatabaseRepository;

@Service
public class MedicationDatabaseService {

	@Autowired
	private MedicationDatabaseRepository medicationDatabaseRepository;

	public List<MedicationDatabaseDto> getDrugDetailsById(String drugId) {
		List<MedicationDatabaseDto> DrugDetails = new ArrayList<>();

		List<MedicationDatabase> drugs = medicationDatabaseRepository.findByDrugId(drugId);

		for (MedicationDatabase drug : drugs) {
			DrugDetails.add(new MedicationDatabaseDto(drug.getDrugId(),drug.getDrugName(), drug.getDrugGenericName(),
					drug.getDrugBrandName(), drug.getDrugStrength(), drug.getDrugForm()));
		}

		return DrugDetails;

	}

	public List<MedicationDto> getDrugDetailsByName(String drugName) {
		List<MedicationDto> DrugDetails = new ArrayList<>();

		List<MedicationDatabase> drugs = medicationDatabaseRepository.findByDrugName(drugName);

		for (MedicationDatabase drug : drugs) {
			DrugDetails.add(new MedicationDto(drug.getDrugId(), drug.getDrugGenericName(), drug.getDrugBrandName(), drug.getDrugStrength(), drug.getDrugForm()));
		}

		return DrugDetails;
	}

	public List<String> getAllDrugNameByKey(String key) {
		return medicationDatabaseRepository.findAllDrugNameContaining(key);
	}

	
	
	public List<MedicationDatabaseDto> getAllMedicationsByKey(String key) {
		List<MedicationDatabaseDto> DrugDetails = new ArrayList<>();

		List<MedicationDatabase> drugs = medicationDatabaseRepository.findAllMedicationsContainingDrugName(key);

		for (MedicationDatabase drug : drugs) {
			DrugDetails.add(new MedicationDatabaseDto(drug.getDrugId(),drug.getDrugName(), drug.getDrugGenericName(),
					drug.getDrugBrandName(), drug.getDrugStrength(), drug.getDrugForm()));
		}

		return DrugDetails;
	
	}

	public List<MedicationDatabaseDto> getAllMedicationsByDrugIdKey(String key) {
		List<MedicationDatabaseDto> DrugDetails = new ArrayList<>();

		List<MedicationDatabase> drugs = medicationDatabaseRepository.findAllMedicationsContainingDrugId(key);

		for (MedicationDatabase drug : drugs) {
			DrugDetails.add(new MedicationDatabaseDto(drug.getDrugId(),drug.getDrugName(), drug.getDrugGenericName(),
					drug.getDrugBrandName(), drug.getDrugStrength(), drug.getDrugForm()));
		}

		return DrugDetails;
	}

}
