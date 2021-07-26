package com.citiustech.hms.Medication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.Medication.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

}
