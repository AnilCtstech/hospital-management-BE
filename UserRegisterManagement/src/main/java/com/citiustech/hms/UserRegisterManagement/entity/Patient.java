package com.citiustech.hms.UserRegisterManagement.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;

@Entity

public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long patientId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	@Email(message = "Email should be valid")
	private String email;

	@Column(nullable = false)
	// DateOfBirth should be Of Date Type
	private String dateOfBirth;

	@Column(nullable = false)
	@Min(value = 1, message = "Age should not be zero")
	private int age;

	@Column(nullable = false)
	private String contactNo;

	// @Column(nullable = false)
	private String gender;

	// @Column(nullable = false)
	private String race;

	// @Column(nullable = false)
	private String ethnicity;
	//
	private String languagesKnown;
	private String homeAddress;
	private String emergTitle;
	private String emergFirstName;
	
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="is_blocked" )
	private Boolean isBlocked;
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getEmergTitle() {
		return emergTitle;
	}

	public void setEmergTitle(String emergTitle) {
		this.emergTitle = emergTitle;
	}

	private String emergLastName;
	private String emergRelationship;
	private String emergContact;
	private String emergEmail;
	public String getEmergEmail() {
		return emergEmail;
	}

	public void setEmergEmail(String emergEmail) {
		this.emergEmail = emergEmail;
	}

	private String emergAddress;
	private String isAccess;
	
	@Type(type = "numeric_boolean")
    private Boolean hasAllergy;

	public Boolean isHasAllergy() {
		return hasAllergy;
	}

	public void setHasAllergy(Boolean hasAllergy) {
		this.hasAllergy = hasAllergy;
	}

	// how2do
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Set<Allergy> allergy;

	public Patient() {
		super();
	}

	public Set<Allergy> getAllergy() {
		return allergy;
	}

	public void setAllergy(Set<Allergy> allergy) {
		this.allergy = allergy;
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