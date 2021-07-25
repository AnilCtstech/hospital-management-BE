package com.citiustech.hms.Medication.dto;

import java.security.Timestamp;

public class MedicationDto {

	private String drugId;

	private String drugGenericName;

	private String drugBrandName;

	private String drugStrength;

	private String drugName;
	
	private long patientId;
	private String drugForm;

	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String createdBy;
	private String updatedBy;

	

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	

	public MedicationDto(String drugId, String drugGenericName, String drugBrandName, String drugStrength,
			String drugForm) {
		super();
		this.drugId = drugId;
		this.drugGenericName = drugGenericName;
		this.drugBrandName = drugBrandName;
		this.drugStrength = drugStrength;
		this.drugForm = drugForm;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugGenericName() {
		return drugGenericName;
	}

	public void setDrugGenericName(String drugGenericName) {
		this.drugGenericName = drugGenericName;
	}

	public String getDrugBrandName() {
		return drugBrandName;
	}

	public void setDrugBrandName(String drugBrandName) {
		this.drugBrandName = drugBrandName;
	}

	public String getDrugStrength() {
		return drugStrength;
	}

	public void setDrugStrength(String drugStrength) {
		this.drugStrength = drugStrength;
	}

	public String getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}

}
