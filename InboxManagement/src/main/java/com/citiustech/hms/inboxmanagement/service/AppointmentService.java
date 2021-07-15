package com.citiustech.hms.inboxmanagement.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		if (bookAppointment.getAppointmentDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return AppointmentStatus.failure;
		}
		return null;
	}

	public List<AppointmentEmployeeResponseDTO> getWeekAppointments(String token) {

		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		LocalDateTime maxLocalDateTime = currentLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println("currentLocalDateTime " + currentLocalDateTime);
		System.out.println("maxLocalDateTime " + maxLocalDateTime);

		List<Appointment> tempList = appointmentRepository
				.findAllByAppointmentDateBetweenOrderByAppointmentDate(currentLocalDateTime, maxLocalDateTime);

		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();

		for (Appointment appointment : tempList) {
			AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
			tempObj.setDate(appointment.getAppointmentDate());
			tempObj.setDescription(appointment.getDescription());
			tempObj.setTime(appointment.getAppointmentTime());
			tempObj.setAppointmentId(appointment.getAppointmentId());
			String response = callGetApi("http://localhost:8080/user/employee/name/" + appointment.getEmployeeId(),
					token);
			tempObj.setPhysician(response);
			tempObj.setEditHistory(appointment.getEditHisotry());
			String patientName = callGetApi("http://localhost:8080/user/patient/name/" + appointment.getEmployeeId(),
					token);
			tempObj.setTitle("Appointment with " + patientName);
			responseList.add(tempObj);
		}
		return responseList;
	}

	public static String callGetApi(String url, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return response.getBody().toString();
	}

}
