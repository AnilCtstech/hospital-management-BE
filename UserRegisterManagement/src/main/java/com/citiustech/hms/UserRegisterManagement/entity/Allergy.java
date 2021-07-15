package com.citiustech.hms.UserRegisterManagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Allergy implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private long id;
	@Column(nullable = false)
	private String allergyId;
	public String getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(nullable = false)
	private String allergyName;   
	@Column(nullable = false)
	private String allergyType;
	private String allergyDescription;
	private String allergyClinicalInformation;
	private Boolean isFatal;
	// @Column(nullable = false)
	private String allergySource;
	private String isOfForms;
	// @Column(nullable = false)
	private String allerginiCity;
	
	
	
	@ManyToOne
	private Patient patient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAllergyType() {
		return allergyType;
	}

	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getAllergySource() {
		return allergySource;
	}

	public void setAllergySource(String allergySource) {
		this.allergySource = allergySource;
	}

	public String getIsOfForms() {
		return isOfForms;
	}

	public void setIsOfForms(String isOfForms) {
		this.isOfForms = isOfForms;
	}

	public String getAllerginiCity() {
		return allerginiCity;
	}

	public void setAllerginiCity(String allerginiCity) {
		this.allerginiCity = allerginiCity;
	}

	public String getAllergyClinicalInformation() {
		return allergyClinicalInformation;
	}

	public void setAllergyClinicalInformation(String allergyClinicalInformation) {
		this.allergyClinicalInformation = allergyClinicalInformation;
	}

	public String getAllergyDescription() {
		return allergyDescription;
	}

	public void setAllergyDescription(String allergyDescription) {
		this.allergyDescription = allergyDescription;
	}

	public Boolean getIsFatal() {
		return isFatal;
	}

	public void setIsFatal(Boolean isFatal) {
		this.isFatal = isFatal;
	}

	@Override
	public String toString() {
		return "Allergy [id=" + id + ", allergyId=" + allergyId + ", allergyName=" + allergyName + ", allergyType="
				+ allergyType + ", allergyDescription=" + allergyDescription + ", allergyClinicalInformation="
				+ allergyClinicalInformation + ", isFatal=" + isFatal + ", allergySource=" + allergySource
				+ ", isOfForms=" + isOfForms + ", allerginiCity=" + allerginiCity + ", patient=" + patient + "]";
	}

}
