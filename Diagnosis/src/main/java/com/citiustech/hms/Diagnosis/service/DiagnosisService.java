package com.citiustech.hms.Diagnosis.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDto;
import com.citiustech.hms.Diagnosis.entity.Diagnosis;
import com.citiustech.hms.Diagnosis.repository.DiagnosisRepository;

@Service

public class DiagnosisService {
	@Autowired
	private DiagnosisRepository diagnosisRepository;

	public ResponseEntity<String> saveDiagnosis(DiagnosisDto diagnosisDto) {
		Diagnosis temp=new Diagnosis();
		temp.setDiagnosisCode(diagnosisDto.getDiagnosisCode());
		temp.setDiagnosisDescription(diagnosisDto.getDiagnosisDescription());
		temp.setDiagnosisIsDeprecated(diagnosisDto.isDiagnosisIsDeprecated());
		temp.setCreatedAt(new Timestamp(new Date().getTime()));
		//temp.setCreatedAt(new Timestamp(new Date().getTime()));
		System.out.println("--------------------------");
		System.out.println(new Timestamp(new Date().getTime()));
		temp.setUpdatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedBy(diagnosisDto.getUpdatedBy());
		temp.setCreatedBy(diagnosisDto.getCreatedBy());
		temp.setPatientId(diagnosisDto.getPatientId());
		diagnosisRepository.save(temp);
		return ResponseEntity.ok("Diagnosis Saved Successfully");
	}
}
