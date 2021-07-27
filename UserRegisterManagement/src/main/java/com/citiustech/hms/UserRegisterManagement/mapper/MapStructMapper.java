package com.citiustech.hms.UserRegisterManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.citiustech.hms.UserRegisterManagement.dto.PatientDetails;
import com.citiustech.hms.UserRegisterManagement.dto.PatientProfile;
import com.citiustech.hms.UserRegisterManagement.dto.Profile;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;
import com.citiustech.hms.UserRegisterManagement.entity.Patient;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	@Mappings({ @Mapping(target = "id", source = "employee.employeeId") })
	public Profile employeeToProfile(Employee employee);

	public PatientProfile patientToProfile(Patient patient);

	
}
