package com.citiustech.hms.inboxmanagement.dto;

public enum Role {

	ADMIN("A"), DOCTOR("D"), NURSE("N"), PATIENT("P");

	Role(String role) {
		this.role = role;
	}

	private String role;

	public String getShortName() {
		return role;
	}

}
