package com.citiustech.hms.inboxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.inboxmanagement.dto.AppointmentEmployeeResponseDTO;
import com.citiustech.hms.inboxmanagement.dto.AppointmentStatus;
import com.citiustech.hms.inboxmanagement.dto.BookAppointment;
import com.citiustech.hms.inboxmanagement.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/book")
	public String createAppointment(@RequestBody BookAppointment bookAppointment) {
		if (bookAppointment.getAppointmentDate() == null || bookAppointment.getAppointmentTime() == null
				|| bookAppointment.getDescription() == null || bookAppointment.getPatientId() == 0
				|| bookAppointment.getEmployeeId() == 0) {
			return AppointmentStatus.attribute;
		} else {
			return appointmentService.createAppointment(bookAppointment);
		}
	}

	@GetMapping("/weekly")
	public List<AppointmentEmployeeResponseDTO> getWeekAppointments() {
		return appointmentService.getWeekAppointments();
	}

	@PutMapping("/book1")
	public String editAppointment(@RequestBody BookAppointment bookAppointment) {
		if (bookAppointment.getAppointmentDate() == null || bookAppointment.getAppointmentTime() == null
				|| bookAppointment.getDescription() == null || bookAppointment.getPatientId() == 0
				|| bookAppointment.getEmployeeId() == 0) {
			return AppointmentStatus.attribute;
		} else {
			return appointmentService.createAppointment(bookAppointment);
		}
	}

}