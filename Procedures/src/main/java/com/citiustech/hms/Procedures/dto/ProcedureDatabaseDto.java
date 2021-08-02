package com.citiustech.hms.Procedures.dto;

public class ProcedureDatabaseDto {
	private String procedureCode;
	private String procedureDescription;
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
	public ProcedureDatabaseDto(String procedureCode, String procedureDescription) {
		super();
		this.procedureCode = procedureCode;
		this.procedureDescription = procedureDescription;
	}
	
}
