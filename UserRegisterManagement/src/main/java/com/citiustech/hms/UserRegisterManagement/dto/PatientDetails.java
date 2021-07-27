package com.citiustech.hms.UserRegisterManagement.dto;

public class PatientDetails {
	
	private Long patientId;
	private String firstName;
	private String email;
	private String status;
	
	
	
	public PatientDetails() {
		super();
		
	}



	public PatientDetails(Long patientId, String firstName, String email, String status) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.email = email;
		this.status = status;
	}



	public Long getPatientId() {
		return patientId;
	}



	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "PatientDetails [patientId=" + patientId + ", firstName=" + firstName + ", email=" + email + ", status="
				+ status + "]";
	}
	
	
	

}
