package com.citiustech.hms.UserRegisterManagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.service.PatientService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
	@Autowired
	private PatientService patientService;

	// creating patient
	@PostMapping("/patient")

	public ResponseEntity<String> createPatient(@RequestBody Patient patientRequest) {

		if (patientRequest.getTitle() == null || patientRequest.getFirstName() == null
				|| patientRequest.getLastName() == null || patientRequest.getPassword() == null
				|| patientRequest.getEmail() == null || patientRequest.getDateOfBirth() == null
				|| patientRequest.getContactNo() == null)

			return ResponseEntity.unprocessableEntity().body("Attributes cannot be Null ");
		else
			return patientService.createPatient(patientRequest);
	}

	// get patient by Id
	// create patient registration dto

	@GetMapping("/patient/{patientId}")
	public Optional<Patient> getPatientById(@PathVariable Long patientId) {
		return patientService.getPatientById(patientId);
	}

	// Delete patient by Id
	@DeleteMapping("/patient/{patientId}")
	public ResponseEntity<Object> deletePatient(@PathVariable Long patientId) {
		return patientService.deletepatient(patientId);
	}

	@PutMapping("/update/patient/{patientId}")

	public ResponseEntity<Object> updateEmployee(@PathVariable Long patientId, @RequestBody Patient patientRequest) {
		return patientService.updatePatient(patientId, patientRequest);
	}

	@GetMapping("/patient/name/{id}")
	public ResponseEntity<String> getPatientNameById(@PathVariable("id") String id) {
		return patientService.getPatientNameById(Long.parseLong(id));
	}

}