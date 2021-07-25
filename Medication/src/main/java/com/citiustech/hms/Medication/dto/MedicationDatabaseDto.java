package com.citiustech.hms.Medication.dto;

public class MedicationDatabaseDto {
	

	private String drugName;

	private String drugGenericName;

	private String drugBrandName;

	private String drugStrength;

	private String drugForm;

	

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

	public MedicationDatabaseDto(String drugName, String drugGenericName, String drugBrandName, String drugStrength,
			String drugForm) {
		super();
		this.drugName = drugName;
		this.drugGenericName = drugGenericName;
		this.drugBrandName = drugBrandName;
		this.drugStrength = drugStrength;
		this.drugForm = drugForm;
	}

}
