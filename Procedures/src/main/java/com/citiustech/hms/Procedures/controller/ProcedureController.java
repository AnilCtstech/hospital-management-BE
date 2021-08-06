package com.citiustech.hms.Procedures.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Procedures.dto.ProcedureDto;
import com.citiustech.hms.Procedures.entity.Procedures;
import com.citiustech.hms.Procedures.repository.ProcedureRepository;
import com.citiustech.hms.Procedures.service.ProcedureService;

@RestController
@RequestMapping("/saveprocedure")
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;

	@Autowired
	private ProcedureRepository procedureRepository;

	@PostMapping("/create")
	public ResponseEntity<String> saveProcedure(@RequestBody List<ProcedureDto> procedureDto) {
		return procedureService.saveProcedure(procedureDto);
	}

	@GetMapping("/procedures/{id}")
	public List<Procedures> getProcedures(@PathVariable long id) {
		return procedureRepository.findByPatientId(id);
	}
}
