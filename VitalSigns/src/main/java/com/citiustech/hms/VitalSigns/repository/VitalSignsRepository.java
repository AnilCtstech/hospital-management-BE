package com.citiustech.hms.VitalSigns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.VitalSigns.entity.VitalSigns;
@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Long> {
	
}
