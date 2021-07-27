package com.citiustech.hms.Diagnosis.dto;

public class DiagnosisDatabaseDto {
	private String diagnosisCode;
	private String diagnosisDescription;
	public DiagnosisDatabaseDto(String diagnosisDescription) {
		super();
		this.diagnosisDescription = diagnosisDescription;
	}
	public DiagnosisDatabaseDto() {
		super();
	}
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
	
	
}
