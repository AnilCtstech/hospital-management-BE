package com.citiustech.hms.UserRegisterManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.dto.PatientDemographics;
import com.citiustech.hms.UserRegisterManagement.dto.PatientDetails;
import com.citiustech.hms.UserRegisterManagement.dto.PatientProfile;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.service.PatientService;
import com.citiustech.hms.UserRegisterManagement.utils.JwtUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private JwtUtil jwtUtil;

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

	@PutMapping("/update/patient")

	public ResponseEntity<Object> updateEmployee(@RequestBody PatientDemographics patientDemographics) {
		return patientService.updatePatient(patientDemographics);
	}

	@GetMapping("/patient/name/{id}")
	public ResponseEntity<String> getPatientNameById(@PathVariable("id") String id) {
		return patientService.getPatientNameById(Long.parseLong(id));
	}
	
	@GetMapping("/patient/all")
	public ResponseEntity<List<PatientProfile>> getPatients(){
		
		List<PatientProfile> patients = patientService.getAllPatient();
		System.out.println("Email :: "+patients.get(0).getEmail());
		return ResponseEntity.ok(patients);
		
	}
	
	@PostMapping("/patient/email")
	public ResponseEntity<List<PatientProfile>> getPatientByEmail(@RequestBody String email) {
		List<PatientProfile> profile = patientService.getPatientByEmail(email);
		return ResponseEntity.ok(profile);
	}
	
	@GetMapping("/patient/getall")
	public List<PatientDetails> getPatient(){
		List<PatientDetails> patientDetails = patientService.getPatientDetails();
		return patientDetails;
		
	}
	
	@PutMapping("/patient/update")
	public ResponseEntity<String> updateStatus(@RequestBody PatientDetails patientDetails){
		String email = patientDetails.getEmail();
		String msg = patientService.updatePatientStatus(email,patientDetails);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}

}