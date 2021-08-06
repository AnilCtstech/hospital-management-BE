package com.citiustech.hms.Procedures.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.Procedures.dto.ProcedureDto;
import com.citiustech.hms.Procedures.entity.Procedures;
import com.citiustech.hms.Procedures.repository.ProcedureRepository;
import com.citiustech.hms.Procedures.util.JwtUtil;

@Service
public class ProcedureService {

	@Autowired
	private ProcedureRepository procedureRepository;

	@Autowired
	private JwtUtil jwtUtil;

	public ResponseEntity<String> saveProcedure(List<ProcedureDto> procedureDto, String authorization) {
		/*
		 * Procedures temp= new Procedures();
		 * temp.setProcedureCode(procedureDto.getProcedureCode());
		 * temp.setProcedureDescription(procedureDto.getProcedureDescription());
		 * temp.setProcedureIsdeprecated(procedureDto.isProcedureIsdeprecated());
		 * temp.setCreatedAt(new Timestamp(new Date().getTime())); temp.setUpdatedAt(new
		 * Timestamp(new Date().getTime()));
		 * temp.setUpdatedBy(procedureDto.getUpdatedBy());
		 * temp.setCreatedBy(procedureDto.getCreatedBy());
		 * temp.setPatientId(procedureDto.getPatientId());
		 * temp.setEmployeeId(procedureDto.getEmployeeId());
		 * procedureRepository.save(temp);
		 */

		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		List<Procedures> procedureList = new ArrayList<>();
		for (ProcedureDto proc : procedureDto) {
			procedureList.add(new Procedures(proc.getProcedureCode(), proc.getProcedureDescription(),
					proc.isProcedureIsdeprecated(), proc.getPatientId(), proc.getEmployeeId(),
					new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), user,
					proc.getUpdatedBy()));
			procedureRepository.saveAll(procedureList);
		}
		return ResponseEntity.ok("Procedure saved successfully");
	}

	public List<ProcedureDto> getAllProcedureByPhysian(String authorization) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		Optional<List<Procedures>> optional = procedureRepository.findAllByCreatedBy(user);
		List<ProcedureDto> procedureDto = new ArrayList<>();

		if (optional.isPresent()) {
			optional.get().forEach(procedure -> {
				procedureDto.add(new ProcedureDto(procedure.getProcedureCode(), procedure.getProcedureDescription(),
						procedure.isProcedureIsdeprecated(), procedure.getPatientId(), procedure.getCreatedAt()));
			});
			return procedureDto;
		}
		return procedureDto;
	}

	public List<Long> getAllPatientByEmployee(String authorization) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		List<Long> patientIds = procedureRepository.findPatientIdByCreatedBy(user);
		return patientIds;
	}

	public List<ProcedureDto> getProcedureByPatient(String authorization, String patientId) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);

		Optional<List<Procedures>> optional = procedureRepository.findAllByCreatedByAndPatient(user,
				Long.parseLong(patientId));

		List<ProcedureDto> procedureDto = new ArrayList<>();
		if (optional.isPresent()) {
			optional.get().forEach(procedure -> {
				procedureDto.add(new ProcedureDto(procedure.getProcedureCode(), procedure.getProcedureDescription(),
						procedure.isProcedureIsdeprecated(), procedure.getPatientId(), procedure.getCreatedAt()));
			});
			return procedureDto;
		}
		return procedureDto;
	}

}
