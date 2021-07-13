package com.citiustech.hms.UserRegisterManagement.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.citiustech.hms.UserRegisterManagement.entity.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role attribute) {
		if (attribute == null)
			return null;
		switch (attribute) {
		case ADMIN:
			return "A";
		case DOCTOR:
			return "D";
		case NURSE:
			return "N";
		case PATIENT:
			return "P";
		default:
			throw new IllegalArgumentException("Role [" + attribute + "] not supported.");
		}
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		switch (dbData) {
		case "A":
			return Role.ADMIN;

		case "D":
			return Role.DOCTOR;

		case "N":
			return Role.NURSE;

		case "P":
			return Role.PATIENT;

		default:
			throw new IllegalArgumentException("Role [" + dbData + "] not supported.");
		}
	}

}
