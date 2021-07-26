package com.citiustech.hms.inboxmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.inboxmanagement.dto.AppointmentEmployeeResponseDTO;
import com.citiustech.hms.inboxmanagement.dto.AppointmentStatus;
import com.citiustech.hms.inboxmanagement.dto.BookAppointment;
import com.citiustech.hms.inboxmanagement.dto.EditAppointment;
import com.citiustech.hms.inboxmanagement.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin("http://localhost:4200")
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
	public List<AppointmentEmployeeResponseDTO> getWeekAppointments(
			@RequestHeader(value = "Authorization") String authToken) {
		// System.out.println("token receivd" + authToken.split(" ")[1]);
		authToken = authToken.split(" ")[1];
		return appointmentService.getWeekAppointments(authToken);
	}

	@PutMapping("/book")
	public String editAppointment(@RequestBody EditAppointment editAppointment) {
		if (editAppointment.getAppointmentDate() == null || editAppointment.getAppointmentTime() == null
				|| editAppointment.getDescription() == null || editAppointment.getPatientId() == 0
				|| editAppointment.getEmployeeId() == 0 || editAppointment.getTimeOfEdit() == null
				|| editAppointment.getEmployeeDetail() == null || editAppointment.getReason() == null) {
			return AppointmentStatus.attribute;
		} else {
			return appointmentService.editAppointment(editAppointment);
		}
	}

	@DeleteMapping("/book/{id}")
	public String deleteAppointment(@PathVariable Long id) {
		return appointmentService.deleteAppointment(id);
	}

	@GetMapping("/patientsearch/{name}")
	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByPatient(@PathVariable String name,
			@RequestHeader(value = "Authorization") String authToken) {
		return appointmentService.searchAppointmentsByPatient(name, authToken.split(" ")[1]);

	}

	@GetMapping("/physiciansearch/{name}")
	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByPhysician(@PathVariable String name,
			@RequestHeader(value = "Authorization") String authToken) {
		return appointmentService.searchAppointmentsByPhysician(name, authToken.split(" ")[1]);

	}

}