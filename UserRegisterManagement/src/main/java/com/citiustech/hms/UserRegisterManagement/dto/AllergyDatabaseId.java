package com.citiustech.hms.UserRegisterManagement.dto;

public class AllergyDatabaseId {
private String allergyId;
private String allergyName;

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
public AllergyDatabaseId(String allergyId, String allergyName) {
	super();
	this.allergyId = allergyId;
	this.allergyName = allergyName;
}
public AllergyDatabaseId() {
	super();
}

}
