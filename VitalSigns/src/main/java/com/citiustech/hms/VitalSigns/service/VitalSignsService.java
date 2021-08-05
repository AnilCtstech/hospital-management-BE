package com.citiustech.hms.VitalSigns.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.VitalSigns.dto.VitalSignsDto;
import com.citiustech.hms.VitalSigns.entity.VitalSigns;
import com.citiustech.hms.VitalSigns.repository.VitalSignsRepository;

@Service
public class VitalSignsService {
	@Autowired
	private VitalSignsRepository vitalSignsRepository;

	public ResponseEntity<String> saveVitalSigns(VitalSignsDto vitalSignsDto) {
		VitalSigns temp= new VitalSigns();
		temp.setHeight(vitalSignsDto.getHeight());
		temp.setBodyTemperature(vitalSignsDto.getBodyTemperature());
		temp.setBloodPressure(vitalSignsDto.getBloodPressure());
		temp.setRespirationRate(vitalSignsDto.getRespirationRate());
		temp.setCreatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedBy(vitalSignsDto.getUpdatedBy());
		temp.setCreatedBy(vitalSignsDto.getCreatedBy());
		temp.setPatientId(vitalSignsDto.getPatientId());
		temp.setWeight(vitalSignsDto.getWeight());
		vitalSignsRepository.save(temp);
		return ResponseEntity.ok("Vital signs saved");
	}
}
