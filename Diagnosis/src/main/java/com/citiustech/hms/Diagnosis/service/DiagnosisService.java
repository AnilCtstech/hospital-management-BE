package com.citiustech.hms.Diagnosis.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Diagnosis.dto.DiagnosisDto;
import com.citiustech.hms.Diagnosis.entity.Diagnosis;
import com.citiustech.hms.Diagnosis.repository.DiagnosisRepository;
import com.citiustech.hms.Diagnosis.util.JwtUtil;

@Service
public class DiagnosisService {
	@Autowired
	private DiagnosisRepository diagnosisRepository;

	@Autowired
	private JwtUtil jwtUtil;

	public ResponseEntity<String> saveDiagnosis(List<DiagnosisDto> diagnosisDto, String authorization) {

		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		List<Diagnosis> diagnosisList = new ArrayList<>();
		for (DiagnosisDto diag : diagnosisDto) {
			diagnosisList.add(new Diagnosis(diag.getDiagnosisCode(), diag.getDiagnosisDescription(),
					diag.isDiagnosisIsDeprecated(), diag.getPatientId(), new Timestamp(new Date().getTime()),
					new Timestamp(new Date().getTime()), user, diag.getUpdatedBy()));
		}

		diagnosisRepository.saveAll(diagnosisList);

		return ResponseEntity.ok("Diagnosis Saved Successfully");
	}

	public List<DiagnosisDto> getAllDiagnosisByPhysian(String authorization) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		Optional<List<Diagnosis>> optional = diagnosisRepository.findAllByCreatedBy(user);

		List<DiagnosisDto> diagnosisDto = new ArrayList<>();
		if (optional.isPresent()) {
			optional.get().forEach(diagnosis -> {
				diagnosisDto.add(new DiagnosisDto(diagnosis.getDiagnosisCode(), diagnosis.getDiagnosisDescription(),
						diagnosis.isDiagnosisIsDeprecated(), diagnosis.getPatientId(), diagnosis.getCreatedAt()));
			});
			return diagnosisDto;
		}
		return diagnosisDto;
	}

	public List<Long> getAllPatientByEmployee(String authorization) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		List<Long> patientIds = diagnosisRepository.findPatientIdByCreatedBy(user);
		return patientIds;
	}

	public List<DiagnosisDto> getDiagnosisByPatient(String authorization, String patientId) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		Optional<List<Diagnosis>> optional = diagnosisRepository.findAllByCreatedByAndPatient(user,
				Long.parseLong(patientId));

		List<DiagnosisDto> diagnosisDto = new ArrayList<>();
		if (optional.isPresent()) {
			optional.get().forEach(diagnosis -> {
				diagnosisDto.add(new DiagnosisDto(diagnosis.getDiagnosisCode(), diagnosis.getDiagnosisDescription(),
						diagnosis.isDiagnosisIsDeprecated(), diagnosis.getPatientId(), diagnosis.getCreatedAt()));
			});
			return diagnosisDto;
		}
		return diagnosisDto;
	}

}
