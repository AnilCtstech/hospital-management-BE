package com.citiustech.hms.Diagnosis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosis_database")
public class DiagnosisDatabase {
	@Id
	private String diagnosisCode;
	private String diagnosisDescription;
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}
	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}
	@Override
	public String toString() {
		return "DiagnosisDatabase [diagnosisCode=" + diagnosisCode + ", diagnosisDescription=" + diagnosisDescription
				+ "]";
	}
	

}
