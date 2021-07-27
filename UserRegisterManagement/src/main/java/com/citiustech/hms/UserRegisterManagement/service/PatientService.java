package com.citiustech.hms.UserRegisterManagement.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.UserRegisterManagement.dto.PatientDemographics;
import com.citiustech.hms.UserRegisterManagement.dto.PatientDetails;
import com.citiustech.hms.UserRegisterManagement.dto.PatientProfile;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;
import com.citiustech.hms.UserRegisterManagement.mapper.MapStructMapper;
import com.citiustech.hms.UserRegisterManagement.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MapStructMapper mapStructMapper;

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
			patient.setIsActive(true);
			patient.setIsBlocked(false);
			patient.setContactNo(patientRequest.getContactNo());
			patient.setGender(patientRequest.getGender());
			patient.setAge(Period.between(
					LocalDate.parse(patientRequest.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDate.now()).getYears());
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
		// if (patientRepository.findById(patientId).isPresent())
		if (patientRepository.findByEmail(patientDemographics.getEmail()).isPresent())

		{
			// Patient newPatient = patientRepository.findById(patientId).get();
			Patient newPatient = patientRepository.findByEmail(patientDemographics.getEmail()).get();
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

			Boolean t = patientDemographics.isHasAllergy();
			if (t.equals(true)) {
				newPatient.setHasAllergy(patientDemographics.isHasAllergy());
				newPatient.setAllergy(patientDemographics.getAllergy());
			}

			Patient savedPatient = patientRepository.save(newPatient);

			if (patientRepository.findById(savedPatient.getPatientId()).isPresent())

				return ResponseEntity.accepted().body("Patient Demographics updated Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating patient specified");
		} else
			return ResponseEntity.unprocessableEntity().body("cannot find the patient specified");

	}

	public List<PatientProfile> getAllPatient() {

		List<Patient> patients = patientRepository.findAll();
		System.out.println("Count :: " + patientRepository.count());

		List<PatientProfile> profiles = new ArrayList<>();

		patients.stream().forEach(e -> {
			profiles.add(mapStructMapper.patientToProfile(e));
		});

		return profiles;

	}

	public List<PatientProfile> getPatientByEmail(String email) {
		List<Patient> patient = patientRepository.findPatientByEmail(email);
		List<PatientProfile> profile = new ArrayList<>();

		patient.stream().forEach(e -> {
			profile.add(mapStructMapper.patientToProfile(e));
		});

		return profile;
	}


	public List<PatientDetails> getPatientDetails() {
		List<Patient> patients=patientRepository.findByOrderByFirstNameAsc();
		
		List<PatientDetails> details = new ArrayList<>();
		for (Patient patient : patients) {
			PatientDetails patientDetails = new PatientDetails();
			patientDetails.setPatientId(patient.getPatientId());
			patientDetails.setFirstName(patient.getFirstName());
			patientDetails.setEmail(patient.getEmail());
			
					if(patient.getIsActive() == true) {
						patientDetails.setStatus("Active");
					}else if(patient.getIsBlocked() == true) {
						patientDetails.setStatus("Blocked");
					}else {
					patientDetails.setStatus("Unknown");
					}
			
			details.add(patientDetails);
		}
		
		return details;
	}

	
	@Transactional
	public String updatePatientStatus(String email, PatientDetails patientDetails) {
		boolean isActive;
		boolean isBlocked;
			if(patientDetails.getStatus().equalsIgnoreCase("Active")) {
				isActive = true;
				isBlocked = false;
			}else {
				isActive = false;
				isBlocked = true;
			}
			patientRepository.updatePatientStatus(email,isActive,isBlocked);
			return "Status Updated";
	}


	public List<Long> getPatientIdByName(String name) {
		// List<Patient> patientList = patientRepository.findByFirstNameContains(name);
		List<Patient> patientList = patientRepository.findByFirstNameIgnoreCaseContaining(name);
//		List<Patient> patientList = patientRepository
//				.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingIn(name);
		List<Long> patientIdList = new ArrayList<>();
		for (Patient patient : patientList) {
			patientIdList.add(patient.getPatientId());
		}
		return patientIdList;
	}


}
