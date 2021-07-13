package com.citiustech.hms.inboxmanagement.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.hms.inboxmanagement.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findAllByAppointmentDateBetween(Date currentTime, Date uptoLastTimeStamp);

	List<Appointment> findAllByAppointmentDateBetweenOrderByAppointmentDate(LocalDateTime currentTime,
			LocalDateTime uptoLastTimeStamp);
}
