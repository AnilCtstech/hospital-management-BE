package com.citiustech.hms.UserRegisterManagement.dto;

import com.citiustech.hms.UserRegisterManagement.entity.Role;

public class PatientProfile {

	private Long patientId;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String dateOfBirth;
	
	private String contactNo;
	
	private String gender;
	
	private String race;
	
	private String ethnicity;
	
	private String homeAddress;

	public PatientProfile(String title, String first_name, String last_name, String email_id, String dateOfBirth,
			String contactNo, String gender, String race, String ethnicity, String homeAddress) {
		super();
		this.title = title;
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email_id;
		this.dateOfBirth = dateOfBirth;
		this.contactNo = contactNo;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		this.homeAddress = homeAddress;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEthnicity() {
		return ethnicity;
	}



	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}



	public String getHomeAddress() {
		return homeAddress;
	}



	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}



	@Override
	public String toString() {
		return "PatientProfile [patient_id=" + patientId + ", title=" + title + ", first_name=" + firstName
				+ ", last_name=" + lastName + ", email_id=" + email + ", dateOfBirth=" + dateOfBirth
				+ ", contactNo=" + contactNo + ", gender=" + gender + ", race=" + race + ", ethnicity=" + ethnicity
				+ ", homeAddress=" + homeAddress + "]";
	}	
		
	
}
