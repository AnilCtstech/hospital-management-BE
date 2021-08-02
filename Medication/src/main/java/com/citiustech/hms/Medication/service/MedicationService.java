package com.citiustech.hms.Medication.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Medication.dto.MedicationDto;
import com.citiustech.hms.Medication.entity.Medication;
import com.citiustech.hms.Medication.repository.MedicationRepository;

@Service
public class MedicationService {
	@Autowired
	private MedicationRepository medicationRepository;

	public ResponseEntity<String> saveMedication(List<MedicationDto> medicationDto) {

		/*
		 * Medication temp= new Medication(); temp.setDrugId(medicationDto.getDrugId());
		 * temp.setDrugName(medicationDto.getDrugName());
		 * temp.setDrugGenericName(medicationDto.getDrugGenericName());
		 * temp.setDrugStrength(medicationDto.getDrugStrength());
		 * temp.setDrugForm(medicationDto.getDrugForm());
		 * temp.setDrugBrandName(medicationDto.getDrugBrandName());
		 * temp.setCreatedAt(new Timestamp(new Date().getTime())); temp.setUpdatedAt(new
		 * Timestamp(new Date().getTime()));
		 * temp.setUpdatedBy(medicationDto.getUpdatedBy());
		 * temp.setCreatedBy(medicationDto.getCreatedBy());
		 * temp.setPatientId(medicationDto.getPatientId());
		 * temp.setEmployeeId(medicationDto.getEmployeeId());
		 * medicationRepository.save(temp);
		 */

		List<Medication> medicationList = new ArrayList<>();
		for (MedicationDto med : medicationDto) {
			medicationList.add(new Medication(med.getDrugId(), med.getDrugName(), med.getDrugGenericName(),
					med.getDrugBrandName(), med.getDrugStrength(), med.getDrugForm(), med.getPatientId(),
					med.getEmployeeId(), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), med.getCreatedBy(), med.getUpdatedBy()));
		}
		medicationRepository.saveAll(medicationList);
		return ResponseEntity.ok("Medication details saved");

	}
}
