package com.citiustech.hms.UserRegisterManagement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public ResponseEntity<String> createPatient(Patient patientRequest) {

		Patient patient = new Patient();
		if (patientRepository.findByEmail(patientRequest.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("the patient is already exist, Failed to create new Patient");
		} else {
			patient.setTitle(patientRequest.getTitle());
			patient.setFirstName(patientRequest.getFirstName());
			patient.setLastName(patientRequest.getLastName());
			patient.setPassword(patientRequest.getPassword());
			patient.setEmail(patientRequest.getEmail());
			patient.setDateOfBirth(patientRequest.getDateOfBirth());

			patient.setContactNo(patientRequest.getContactNo());

			Patient savedPatient = patientRepository.save(patient);

			if (patientRepository.findById(savedPatient.getPatientId()).isPresent())
				return ResponseEntity.ok("Patient Created successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed Creating Patient as specified");

		}

	}

	public Optional<Patient> getPatientById(Long patientId) {
		return patientRepository.findById(patientId);
	}

	public ResponseEntity<Object> deletepatient(Long patientId) {
		if (patientRepository.findById(patientId).isPresent()) {
			patientRepository.deleteById(patientId);
			if (patientRepository.findById(patientId).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to delete the specified Patient");
			else
				return ResponseEntity.ok().body("Successfully deleted specified patient");
		} else
			return ResponseEntity.badRequest().body("Cannot find patient specified");
	}

	@Transactional
	public ResponseEntity<Object> updatePatient(Long patientId, Patient patientRequest) {
		if (patientRepository.findById(patientId).isPresent()) {
			Patient newPatient = patientRepository.findById(patientId).get();
			newPatient.setTitle(patientRequest.getTitle());
			newPatient.setFirstName(patientRequest.getFirstName());
			newPatient.setLastName(patientRequest.getLastName());
			newPatient.setPassword(patientRequest.getPassword());
			newPatient.setEmail(patientRequest.getEmail());
			newPatient.setDateOfBirth(patientRequest.getDateOfBirth());
			newPatient.setAge(patientRequest.getAge());
			newPatient.setContactNo(patientRequest.getContactNo());
			newPatient.setGender(patientRequest.getGender());
			newPatient.setRace(patientRequest.getRace());
			newPatient.setEthnicity(patientRequest.getEthnicity());
			newPatient.setLanguagesKnown(patientRequest.getLanguagesKnown());
			newPatient.setHomeAddress(patientRequest.getHomeAddress());
			newPatient.setEmergFirstName(patientRequest.getEmergFirstName());
			newPatient.setEmergLastName(patientRequest.getEmergLastName());
			newPatient.setEmergRelationship(patientRequest.getEmergRelationship());
			newPatient.setEmergContact(patientRequest.getEmergContact());
			newPatient.setEmergAddress(patientRequest.getEmergAddress());
			newPatient.setIsAccess(patientRequest.getIsAccess());

			Patient savedPatient = patientRepository.save(newPatient);
			if (patientRepository.findById(savedPatient.getPatientId()).isPresent())
				return ResponseEntity.accepted().body("Patient updated Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating patient specified");
		} else
			return ResponseEntity.unprocessableEntity().body("cannot find the patient specified");

	}

}