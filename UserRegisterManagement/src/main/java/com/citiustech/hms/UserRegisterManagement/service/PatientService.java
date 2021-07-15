package com.citiustech.hms.UserRegisterManagement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.dto.PatientDemographics;
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

	public ResponseEntity<String> getPatientNameById(long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			String name = patient.get().getFirstName() + " " + patient.get().getLastName();
			return ResponseEntity.ok(name);
		}
		return null;
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
	public ResponseEntity<Object> updatePatient(PatientDemographics patientDemographics) {
	//	if (patientRepository.findById(patientId).isPresent()) 
			if (patientRepository.findByEmail(patientDemographics.getEmail()).isPresent())
				
		{
			//Patient newPatient = patientRepository.findById(patientId).get();
			Patient newPatient= patientRepository.findByEmail(patientDemographics.getEmail()).get();
			newPatient.setTitle(patientDemographics.getTitle());
			newPatient.setFirstName(patientDemographics.getFirstName());
			newPatient.setLastName(patientDemographics.getLastName());
			newPatient.setDateOfBirth(patientDemographics.getDateOfBirth());
			newPatient.setGender(patientDemographics.getGender());
			newPatient.setRace(patientDemographics.getRace());
			newPatient.setEthnicity(patientDemographics.getEthnicity());
			newPatient.setLanguagesKnown(patientDemographics.getLanguagesKnown());
			newPatient.setHomeAddress(patientDemographics.getHomeAddress());
			newPatient.setContactNo(patientDemographics.getContactNo());
			newPatient.setEmergTitle(patientDemographics.getEmergTitle());
			newPatient.setEmergFirstName(patientDemographics.getEmergFirstName());
			newPatient.setEmergLastName(patientDemographics.getEmergLastName());
			newPatient.setEmergRelationship(patientDemographics.getEmergRelationship());
			newPatient.setEmergEmail(patientDemographics.getEmergEmail());
			newPatient.setEmergContact(patientDemographics.getEmergContact());
			newPatient.setEmergAddress(patientDemographics.getEmergAddress());
			newPatient.setIsAccess(patientDemographics.getIsAccess());

			Boolean t=patientDemographics.isHasAllergy();
			if (t.equals(true)) {
			newPatient.setHasAllergy(patientDemographics.isHasAllergy());
			newPatient.setAllergy(patientDemographics.getAllergy());
			}
			System.out.println("+++");
			System.out.println(newPatient.getAllergy().toString());
			Patient savedPatient = patientRepository.save(newPatient);
			
			
			
			if (patientRepository.findById(savedPatient.getPatientId()).isPresent())
				
				return ResponseEntity.accepted().body("Patient Demographics updated Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating patient specified");
		} else
			return ResponseEntity.unprocessableEntity().body("cannot find the patient specified");
		

	}


}
