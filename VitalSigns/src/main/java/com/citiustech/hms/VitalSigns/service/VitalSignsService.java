package com.citiustech.hms.VitalSigns.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.citiustech.hms.VitalSigns.dto.VitalSignsDto;
import com.citiustech.hms.VitalSigns.entity.VitalSigns;
import com.citiustech.hms.VitalSigns.repository.VitalSignsRepository;
import com.citiustech.hms.VitalSigns.util.JwtUtil;

@Service
public class VitalSignsService {
	@Autowired
	private VitalSignsRepository vitalSignsRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	public ResponseEntity<String> saveVitalSigns(VitalSignsDto vitalSignsDto,String authorization) {
		
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);
		
		VitalSigns temp= new VitalSigns();
		temp.setHeight(vitalSignsDto.getHeight());
		temp.setBodyTemperature(vitalSignsDto.getBodyTemperature());
		temp.setBloodPressure(vitalSignsDto.getBloodPressure());
		temp.setRespirationRate(vitalSignsDto.getRespirationRate());
		temp.setCreatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedAt(new Timestamp(new Date().getTime()));
		temp.setUpdatedBy(vitalSignsDto.getUpdatedBy());
		temp.setCreatedBy(user);
		temp.setPatientId(vitalSignsDto.getPatientId());
		temp.setWeight(vitalSignsDto.getWeight());
		vitalSignsRepository.save(temp);
		return ResponseEntity.ok("Vital signs saved");
	}
	
	
	public List<VitalSignsDto> getVitalSignsByPatient(String authorization, String patientId) {
		String token = authorization.substring(7);

		String user = jwtUtil.extractUsername(token);
		
		Optional<List<VitalSigns>> optional = vitalSignsRepository.findAllByCreatedByAndPatient(user,Long.parseLong(patientId));
	System.out.println(optional.isEmpty());
		List<VitalSignsDto> vitalSignsDto = new ArrayList<>();
		if (optional.isPresent()) {
			optional.get().forEach(vitalSigns -> {
				vitalSignsDto.add(
						new VitalSignsDto(vitalSigns.getHeight(), vitalSigns.getWeight(), vitalSigns.getRespirationRate(), vitalSigns.getBodyTemperature(), vitalSigns.getRespirationRate(), vitalSigns.getPatientId(),vitalSigns.getCreatedAt() )
						);
			});
			return vitalSignsDto;
		}
		return vitalSignsDto;
	}
	
	
	
}
