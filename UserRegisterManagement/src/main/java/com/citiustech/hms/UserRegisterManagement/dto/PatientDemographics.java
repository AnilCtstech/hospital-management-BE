package com.citiustech.hms.UserRegisterManagement.dto;

import java.util.Set;

import com.citiustech.hms.UserRegisterManagement.entity.Allergy;

public class PatientDemographics {

	
	private int age;
	private String gender;
	private String race;
	private String ethnicity;
	private String languagesKnown;
	private String homeAddress;
	private String emergFirstName;
	private String emergLastName;
	private String emergRelationship;
	private String emergContact;
	private String emergAddress;
	private String isAccess;
	private Set<Allergy> allergy;
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
