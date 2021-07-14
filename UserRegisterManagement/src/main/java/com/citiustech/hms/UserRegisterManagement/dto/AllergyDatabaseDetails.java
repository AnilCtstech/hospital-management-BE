package com.citiustech.hms.UserRegisterManagement.dto;

public class AllergyDatabaseDetails {
	
	private String allergyType;
	private String allergyName;
	private String allergenSource;
	private String isoFormsOrPartialSequencesOfAllergen;
	private String allerginCity;
	
	
	public AllergyDatabaseDetails() {
		super();
	}
	
	
	
	
	
	public AllergyDatabaseDetails(String allergyType, String allergyName, String allergenSource,
			String isoFormsOrPartialSequencesOfAllergen, String allerginCity) {
		super();
		this.allergyType = allergyType;
		this.allergyName = allergyName;
		this.allergenSource = allergenSource;
		this.isoFormsOrPartialSequencesOfAllergen = isoFormsOrPartialSequencesOfAllergen;
		this.allerginCity = allerginCity;
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
	public String getAllergenSource() {
		return allergenSource;
	}
	public void setAllergenSource(String allergenSource) {
		this.allergenSource = allergenSource;
	}
	public String getIsoFormsOrPartialSequencesOfAllergen() {
		return isoFormsOrPartialSequencesOfAllergen;
	}
	public void setIsoFormsOrPartialSequencesOfAllergen(String isoFormsOrPartialSequencesOfAllergen) {
		this.isoFormsOrPartialSequencesOfAllergen = isoFormsOrPartialSequencesOfAllergen;
	}
	public String getAllerginCity() {
		return allerginCity;
	}
	public void setAllerginCity(String allerginCity) {
		this.allerginCity = allerginCity;
	}
	
}
