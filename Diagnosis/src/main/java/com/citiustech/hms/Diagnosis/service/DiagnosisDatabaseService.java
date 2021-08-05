package com.citiustech.hms.Diagnosis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<String> getDiagnosisDescriptionByKey(String key) {
		
		return diagnosisDatabaseRepository.findAllByDiagnosisDescriptionContaining(key);
	}

}
