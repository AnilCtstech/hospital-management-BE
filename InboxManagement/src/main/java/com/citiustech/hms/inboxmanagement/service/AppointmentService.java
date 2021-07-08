package com.citiustech.hms.inboxmanagement.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.hms.inboxmanagement.dto.AppointmentEmployeeResponseDTO;
import com.citiustech.hms.inboxmanagement.dto.AppointmentStatus;
import com.citiustech.hms.inboxmanagement.dto.BookAppointment;
import com.citiustech.hms.inboxmanagement.entity.Appointment;
import com.citiustech.hms.inboxmanagement.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public String createAppointment(BookAppointment bookAppointment) {
		if (bookAppointment.getAppointmentDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return AppointmentStatus.failure;
		}
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(bookAppointment.getAppointmentDate());
		appointment.setAppointmentTime(bookAppointment.getAppointmentTime());
		appointment.setDescription(bookAppointment.getDescription());
		appointment.setEmployeeId(bookAppointment.getEmployeeId());
		appointment.setPatientId(bookAppointment.getPatientId());
		appointment.setCreatedAt(new Timestamp(new Date().getTime()));
		appointment.setCreatedBy("Physician");
		appointment.setUpdatedAt(new Timestamp(new Date().getTime()));
		appointment.setUpdatedBy("Physician");
		appointmentRepository.save(appointment);
		return AppointmentStatus.success;
	}

	public String editAppointment(BookAppointment bookAppointment) {
		return null;
	}

	public List<AppointmentEmployeeResponseDTO> getWeekAppointments() {
		/*
		 * LocalDate localDate = LocalDate.now(); java.sql.Date currentDate =
		 * java.sql.Date.valueOf(localDate); LocalDate finalLocalDate =
		 * localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)); java.sql.Date
		 * finalDate = java.sql.Date.valueOf(finalLocalDate);
		 */

//		List<Appointment> findAllByAppointmentDateBetween = appointmentRepository
//				.findAllByAppointmentDateBetween(currentDate, finalDate);

		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		LocalDateTime maxLocalDateTime = currentLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

		List<Appointment> tempList = appointmentRepository
				.findAllByAppointmentDateBetweenOrderByAppointmentDate(currentLocalDateTime, maxLocalDateTime);

		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();

		for (Appointment appointment : tempList) {
			AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
			tempObj.setDate(appointment.getAppointmentDate());
			tempObj.setDescription(appointment.getDescription());
			tempObj.setTime(appointment.getAppointmentTime());
			tempObj.setAppointmentId(appointment.getAppointmentId());
			responseList.add(tempObj);
		}
		return responseList;
	}

}
