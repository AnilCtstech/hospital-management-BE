package com.citiustech.hms.patientvisitdetails.entity;

import java.security.Timestamp;

public class Procedures {
private String procedureCode;
private String procedureDescription;
private boolean procedureIsdeprecated;
private long patientId;
private Timestamp createdAt;
private Timestamp updatedAt;
private String createdBy;
private String updatedBy;
public String getProcedureCode() {
	return procedureCode;
}
public void setProcedureCode(String procedureCode) {
	this.procedureCode = procedureCode;
}
public String getProcedureDescription() {
	return procedureDescription;
}
public void setProcedureDescription(String procedureDescription) {
	this.procedureDescription = procedureDescription;
}
public boolean isProcedureIsdeprecated() {
	return procedureIsdeprecated;
}
public void setProcedureIsdeprecated(boolean procedureIsdeprecated) {
	this.procedureIsdeprecated = procedureIsdeprecated;
}
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
}
