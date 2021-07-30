package com.citiustech.hms.Diagnosis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDatabaseDto;
import com.citiustech.hms.Diagnosis.entity.DiagnosisDatabase;
import com.citiustech.hms.Diagnosis.repository.DiagnosisDatabaseRepository;

@Service
public class DiagnosisDatabaseService {
	@Autowired
	private DiagnosisDatabaseRepository diagnosisDatabaseRepository;

	public List<String> getAllDiagnosisCode() {
		return diagnosisDatabaseRepository.findAllByDiagnosisCode();
	}

	public String getDiagnosisDescription(String diagnosisCode) {
	
	DiagnosisDatabase diagnosisDatabase= diagnosisDatabaseRepository.findByDiagnosisCode(diagnosisCode) ;
		return diagnosisDatabase.getDiagnosisDescription();
	}

	public List<DiagnosisDatabaseDto> getDiagnosisDescriptionByKey(String key) {
		
		List<DiagnosisDatabase> result= diagnosisDatabaseRepository.findAllByDiagnosisDescriptionContaining(key);
		List<DiagnosisDatabaseDto> diagnosis= new ArrayList<>();
		for(DiagnosisDatabase diag:result) {
			diagnosis.add(new DiagnosisDatabaseDto(diag.getDiagnosisCode(), diag.getDiagnosisDescription()));

		}
		
		return diagnosis;
	
	}

	public List<DiagnosisDatabaseDto> getDiagnosisCodeByKey(String key) {
		List<DiagnosisDatabase> result= diagnosisDatabaseRepository.findAllByDiagnosisCodeContaining(key);
		List<DiagnosisDatabaseDto> diagnosis= new ArrayList<>();
		for(DiagnosisDatabase diag:result) {
			diagnosis.add(new DiagnosisDatabaseDto(diag.getDiagnosisCode(), diag.getDiagnosisDescription()));

		}
		
		return diagnosis;
	}
	
	
	

}
