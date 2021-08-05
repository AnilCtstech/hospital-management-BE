package com.citiustech.hms.Procedures.entity;



import java.sql.Timestamp;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Procedures {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String procedureCode;
	private String procedureDescription;
	private boolean procedureIsdeprecated;
	private long patientId;
	private long employeeId;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

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

	public Procedures(String procedureCode, String procedureDescription, boolean procedureIsdeprecated, long patientId,
			long employeeId, Timestamp createdAt, Timestamp updatedAt, String createdBy, String updatedBy) {
		super();
		this.procedureCode = procedureCode;
		this.procedureDescription = procedureDescription;
		this.procedureIsdeprecated = procedureIsdeprecated;
		this.patientId = patientId;
		this.employeeId = employeeId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public Procedures() {
		super();
	}
	
}
