package com.citiustech.hms.UserRegisterManagement.dto;

public class AllergyDetails {
	private String allergyId;
	private String allergyName;   
	private String allergyType;
	private String allergyDescription;
	private String allergyClinicalInformation;
	private Boolean isFatal;

	public String getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyType() {
		return allergyType;
	}
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}
	public String getAllergyDescription() {
		return allergyDescription;
	}
	public void setAllergyDescription(String allergyDescription) {
		this.allergyDescription = allergyDescription;
	}
	public String getAllergyClinicalInformation() {
		return allergyClinicalInformation;
	}
	public void setAllergyClinicalInformation(String allergyClinicalInformation) {
		this.allergyClinicalInformation = allergyClinicalInformation;
	}
	public Boolean getIsFatal() {
		return isFatal;
	}
	public void setIsFatal(Boolean isFatal) {
		this.isFatal = isFatal;
	}
	
}
