package com.citiustech.hms.VitalSigns.dto;

import java.sql.Timestamp;

public class VitalSignsDto {
	
	private int height;
	private int weight;
	private String bloodPressure;
	private String bodyTemperature;
	private String respirationRate;
	private long patientId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String createdBy;
	private String updatedBy;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getBodyTemperature() {
		return bodyTemperature;
	}
	public void setBodyTemperature(String bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}
	public String getRespirationRate() {
		return respirationRate;
	}
	public void setRespirationRate(String respirationRate) {
		this.respirationRate = respirationRate;
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
	public VitalSignsDto(int height, int weight, String bloodPressure, String bodyTemperature, String respirationRate,
			long patientId, Timestamp createdAt) {
		super();
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respirationRate = respirationRate;
		this.patientId = patientId;
		this.createdAt = createdAt;
	}
	
	

}
