package com.citiustech.hms.UserRegisterManagement.dto;

import java.util.Set;

import javax.validation.constraints.Email;

import com.citiustech.hms.UserRegisterManagement.entity.Allergy;

public class PatientDemographics {

	private String title;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private int age;
	private String gender;
	private String race;
	private String ethnicity;
	@Email(message = "Email should be valid")
	private String email;
	private String languagesKnown;
	private String homeAddress;
	private String contactNo;
	private String emergTitle;
	private String emergFirstName;
	private String emergLastName;
	private String emergRelationship;
	private String emergContact;
	@Email(message = "Email should be valid")
	private String emergEmail;
	private String emergAddress;
	private boolean hasAllergy;
	private String isAccess;
	private Set<Allergy> allergy;

	public String getEmergTitle() {
		return emergTitle;
	}

	public void setEmergTitle(String emergTitle) {
		this.emergTitle = emergTitle;
	}

	public String getTitle() {
		return title;
	}

	public boolean isHasAllergy() {
		return hasAllergy;
	}

	public void setHasAllergy(boolean hasAllergy) {
		this.hasAllergy = hasAllergy;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;

	}

	public String getEmergEmail() {
		return emergEmail;
	}

	public void setEmergEmail(String emergEmail) {
		this.emergEmail = emergEmail;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public Set<Allergy> getAllergy() {
		return allergy;
	}

	public void setAllergy(Set<Allergy> allergy) {
		this.allergy = allergy;
	}



}