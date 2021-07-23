package com.citiustech.hms.inboxmanagement.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.citiustech.hms.inboxmanagement.dto.EditAppointment;
import com.citiustech.hms.inboxmanagement.dto.ExtractRequest;
import com.citiustech.hms.inboxmanagement.entity.Appointment;
import com.citiustech.hms.inboxmanagement.entity.EditHistory;
import com.citiustech.hms.inboxmanagement.repository.AppointmentRepository;
import com.citiustech.hms.inboxmanagement.repository.SlotRepository;

import io.jsonwebtoken.Claims;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private SlotRepository slotRepository;

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
		appointment.setSlotId(bookAppointment.getSlotId());
		appointment.setMeetingTitle(bookAppointment.getMeetingTitle());
		appointmentRepository.save(appointment);
		return AppointmentStatus.success;
	}

	public String editAppointment(EditAppointment editAppointment) {
		if (editAppointment.getAppointmentDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
			return AppointmentStatus.failure;
		}
		Optional<Appointment> result = appointmentRepository.findById(editAppointment.getAppointmentId());
		// Appointment appointment = new Appointment();
		if (result.isPresent()) {
			Appointment appointment = result.get();
			appointment.setAppointmentDate(editAppointment.getAppointmentDate());
			appointment.setAppointmentTime(editAppointment.getAppointmentTime());
			appointment.setDescription(editAppointment.getDescription());
			appointment.setEmployeeId(editAppointment.getEmployeeId());
			appointment.setPatientId(editAppointment.getPatientId());
			appointment.setCreatedAt(new Timestamp(new Date().getTime()));
			appointment.setCreatedBy("Physician");
			appointment.setUpdatedAt(new Timestamp(new Date().getTime()));
			appointment.setUpdatedBy("Physician");
			appointment.setSlotId(editAppointment.getSlotId());
			appointment.setMeetingTitle(editAppointment.getMeetingTitle());
			Set<EditHistory> editHistorySet = appointment.getEditHistory();
			for (EditHistory tempEditHistory : editHistorySet) {
				System.out.println(tempEditHistory);
			}
			EditHistory editHistory = new EditHistory(editAppointment.getTimeOfEdit(),
					editAppointment.getEmployeeDetail(), editAppointment.getReason(), appointment);
			editHistory.setPreviousAppintmentDetails(appointment.toString());
			editHistorySet.add(editHistory);
			for (EditHistory tempEditHistory : editHistorySet) {
				System.out.println(tempEditHistory);
			}
			appointment.setEditHistory(editHistorySet);
			appointmentRepository.save(appointment);

//			if (editHistorySet != null) {
//				EditHistory editHistory = new EditHistory(editAppointment.getTimeOfEdit(),
//						editAppointment.getEmployeeDetail(), editAppointment.getReason(), appointment);
//				editHistorySet.add(editHistory);
//				appointmentRepository.save(appointment);
//			} else {
//				EditHistory editHistory = new EditHistory(editAppointment.getTimeOfEdit(),
//						editAppointment.getEmployeeDetail(), editAppointment.getReason(), appointment);
//				editHistorySet.set(editHistory);
//				appointmentRepository.save(appointment);
//			}

			return AppointmentStatus.success;
		}
		return AppointmentStatus.failure;

	}

	public List<AppointmentEmployeeResponseDTO> getWeekAppointments(String token) {
		String url_claims = "http://localhost:8088/extract_claims";
		String claims = getClaimForToken(token, url_claims);
		long userId = Long.parseLong(claims.split(",")[0]);
		String role = claims.split(",")[1];
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		LocalDateTime maxLocalDateTime = currentLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println("currentLocalDateTime " + currentLocalDateTime);
		System.out.println("maxLocalDateTime " + maxLocalDateTime);

//		List<Appointment> tempList2 = appointmentRepository
//				.findAllByAppointmentDateBetweenOrderByAppointmentDate(currentLocalDateTime, maxLocalDateTime);

		// start
		List<Appointment> tempList = null;
		if (role.equals("D") || role.equals("N")) {
			tempList = appointmentRepository.findAllByEmployeeIdAndAppointmentDateBetweenOrderByAppointmentDate(userId,
					currentLocalDateTime, maxLocalDateTime);
		} else {
			tempList = appointmentRepository.findAllByPatientIdAndAppointmentDateBetweenOrderByAppointmentDate(userId,
					currentLocalDateTime, maxLocalDateTime);
		}

//		for (Appointment appointment : tempList2) {
//			System.out.println(appointment);
//		}
		// end

		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();

		for (Appointment appointment : tempList) {
			AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
			tempObj.setDate(appointment.getAppointmentDate());
			tempObj.setDescription(appointment.getDescription());
			tempObj.setTime(appointment.getAppointmentTime());
			tempObj.setTime(slotRepository.findById(appointment.getSlotId()).get().getSlots());
			tempObj.setAppointmentId(appointment.getAppointmentId());
			String response = callGetApi("http://localhost:8080/user/employee/name/" + appointment.getEmployeeId(),
					token);
			tempObj.setPhysician(response);
			Set<EditHistory> editHistorySet = appointment.getEditHistory();
			tempObj.setEditHistory(appointment.getEditHistory());
			String patientName = callGetApi("http://localhost:8080/user/patient/name/" + appointment.getPatientId(),
					token);
			tempObj.setTitle("Appointment with " + patientName);
			tempObj.setTitle(appointment.getMeetingTitle());
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

	public String deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
		return "Deleted successfully";
	}

	public static String getClaimForToken(String token, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ExtractRequest request = new ExtractRequest();
		request.setToken(token);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		return response.getBody();
	}

}
