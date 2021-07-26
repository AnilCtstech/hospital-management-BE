package com.citiustech.hms.Procedures.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ProcedureDatabase {
	@Id
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
	
}
