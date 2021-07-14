package com.citiustech.hms.UserRegisterManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AllergyDatabase {

@Id
private String uuid;
private String allergyId;
private String allergyType;
private String allergyName;
private String allergenSource;
private String isoFormsOrPartialSequencesOfAllergen;
private String allerginCity;
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


public String getAllergyId() {
	return allergyId;
}
public void setAllergyId(String allergyId) {
	this.allergyId = allergyId;
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
public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}



}
