package com.citiustech.hms.Diagnosis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Diagnosis.entity.DiagnosisDatabase;
import com.citiustech.hms.Diagnosis.repository.DiagnosisDatabaseRepository;

@Service
public class DiagnosisDatabaseService {
	@Autowired
	private DiagnosisDatabaseRepository diagnosisRepository;

	public List<String> getAllDiagnosisCode() {
		return diagnosisRepository.findAllByDiagnosisCode();
	}

	public String getDiagnosisDescription(String diagnosisCode) {
	
	DiagnosisDatabase diagnosisDatabase= diagnosisRepository.findByDiagnosisCode(diagnosisCode) ;
		return diagnosisDatabase.getDiagnosisDescription();
	}

}
