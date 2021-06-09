package com.citiustech.hms.UserRegisterManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity

public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long patientId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Email(message = "Email should be valid")
	private String email;
	
	@Column(nullable = false)
	//DateOfBirth should be Of Date Type
	private String dateOfBirth;
	
	@Column(nullable = false)
	@Min(value = 1, message = "Age should not be zero")
	private int age;
	
	@Column(nullable = false)
	private String contactNo;
	
	//@Column(nullable = false)
	private String gender;
	
	//@Column(nullable = false)
	private String race;
	
	//@Column(nullable = false)
	private String ethnicity;
	private String languagesKnown;
	private String homeAddress;
	private String emergFirstName;
	private String emergLastName;
	private String emergRelationship;
	private String emergContact;
	private String emergAddress;
	private String isAccess;
	
	public Patient() {
		super();
	}

	public Patient(Long patientId, String title, String firstName, String lastName, String password,
			@Email(message = "Email should be valid") String email, String dateOfBirth,
			@Min(value = 1, message = "Age should not be zero") int age, String contactNo, String gender, String race,
			String ethnicity, String languagesKnown, String homeAddress, String emergFirstName, String emergLastName,
			String emergRelationship, String emergContact, String emergAddress, String isAccess) {
		super();
		this.patientId = patientId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.contactNo = contactNo;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		this.languagesKnown = languagesKnown;
		this.homeAddress = homeAddress;
		this.emergFirstName = emergFirstName;
		this.emergLastName = emergLastName;
		this.emergRelationship = emergRelationship;
		this.emergContact = emergContact;
		this.emergAddress = emergAddress;
		this.isAccess = isAccess;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getLanguagesKnown() {
		return languagesKnown;
	}

	public void setLanguagesKnown(String languagesKnown) {
		this.languagesKnown = languagesKnown;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEmergFirstName() {
		return emergFirstName;
	}

	public void setEmergFirstName(String emergFirstName) {
		this.emergFirstName = emergFirstName;
	}

	public String getEmergLastName() {
		return emergLastName;
	}

	public void setEmergLastName(String emergLastName) {
		this.emergLastName = emergLastName;
	}

	public String getEmergRelationship() {
		return emergRelationship;
	}

	public void setEmergRelationship(String emergRelationship) {
		this.emergRelationship = emergRelationship;
	}

	public String getEmergContact() {
		return emergContact;
	}

	public void setEmergContact(String emergContact) {
		this.emergContact = emergContact;
	}

	public String getEmergAddress() {
		return emergAddress;
	}

	public void setEmergAddress(String emergAddress) {
		this.emergAddress = emergAddress;
	}

	public String getIsAccess() {
		return isAccess;
	}

	public void setIsAccess(String isAccess) {
		this.isAccess = isAccess;
	}
	
	
}
