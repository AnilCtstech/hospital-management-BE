package com.citiustech.hms.patientvisitdetails.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MedicationDatabase {
	@Id
	private String uuid;
	
	private String drugId;
	
	private String drugName;
	
	private String drugGenericName;
	
	private String drugBrandName;

	private String drugStrength;
	
	private String drugForm;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
