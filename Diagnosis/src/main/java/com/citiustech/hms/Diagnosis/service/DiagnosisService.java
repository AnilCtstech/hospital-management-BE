package com.citiustech.hms.Diagnosis.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDatabaseDto;
import com.citiustech.hms.Diagnosis.dto.DiagnosisDto;
import com.citiustech.hms.Diagnosis.entity.Diagnosis;
import com.citiustech.hms.Diagnosis.entity.DiagnosisDatabase;
import com.citiustech.hms.Diagnosis.repository.DiagnosisRepository;

@Service
public class DiagnosisService {
	@Autowired
	private DiagnosisRepository diagnosisRepository;

	public ResponseEntity<String> saveDiagnosis(List<DiagnosisDto> diagnosisDto) {
				
		
		List<Diagnosis> diagnosisList= new ArrayList<>();
		for(DiagnosisDto diag:diagnosisDto) {
			diagnosisList.add(new Diagnosis(diag.getDiagnosisCode(), diag.getDiagnosisDescription(), 
					diag.isDiagnosisIsDeprecated(), diag.getPatientId(),new Timestamp(new Date().getTime()) ,
					new Timestamp(new Date().getTime()), diag.getCreatedBy(), diag.getUpdatedBy()));
		}
			
		diagnosisRepository.saveAll(diagnosisList);
		
		return ResponseEntity.ok("Diagnosis Saved Successfully");
	}
}
