package com.citiustech.hms.UserRegisterManagement.mapper;

import org.mapstruct.Mapper;

import com.citiustech.hms.UserRegisterManagement.dto.Profile;
import com.citiustech.hms.UserRegisterManagement.entity.Employee;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	public Profile employeeToProfile(Employee employee);
}
