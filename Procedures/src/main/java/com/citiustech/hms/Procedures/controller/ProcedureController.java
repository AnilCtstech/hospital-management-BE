package com.citiustech.hms.Procedures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.Procedures.dto.ProcedureDto;
import com.citiustech.hms.Procedures.service.ProcedureService;

@RestController
@RequestMapping("/saveprocedure")
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveProcedure(@RequestBody ProcedureDto procedureDto){
		return procedureService.saveProcedure(procedureDto);
	}
}
