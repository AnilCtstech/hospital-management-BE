package com.citiustech.hms.inboxmanagement.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.citiustech.hms.inboxmanagement.dto.AppointmentEmployeeResponseDTO;
import com.citiustech.hms.inboxmanagement.dto.AppointmentStatus;
import com.citiustech.hms.inboxmanagement.dto.BookAppointment;
import com.citiustech.hms.inboxmanagement.dto.EditAppointment;
import com.citiustech.hms.inboxmanagement.dto.EmailDTO;
import com.citiustech.hms.inboxmanagement.dto.ExtractRequest;
import com.citiustech.hms.inboxmanagement.entity.Appointment;
import com.citiustech.hms.inboxmanagement.entity.EditHistory;
import com.citiustech.hms.inboxmanagement.entity.Slot;
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
		appointment.setCreatedBy("Nurse");
		appointment.setUpdatedAt(new Timestamp(new Date().getTime()));
		appointment.setUpdatedBy("Nurse");
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
			appointment.setCreatedBy("Nurse");
			appointment.setUpdatedAt(new Timestamp(new Date().getTime()));
			appointment.setUpdatedBy("Nurse");
			appointment.setSlotId(editAppointment.getSlotId());
			appointment.setMeetingTitle(editAppointment.getMeetingTitle());
			Set<EditHistory> editHistorySet = appointment.getEditHistory();
			for (EditHistory tempEditHistory : editHistorySet) {
				System.out.println(tempEditHistory);
			}
			LocalTime myObj = LocalTime.now();
			EditHistory editHistory = new EditHistory(myObj,
					editAppointment.getEmployeeDetail(), editAppointment.getReason(), appointment);
			editHistory.setPreviousAppintmentDetails(appointment.toString());
			editHistorySet.add(editHistory);
			for (EditHistory tempEditHistory : editHistorySet) {
				System.out.println(tempEditHistory);
			}
			appointment.setEditHistory(editHistorySet);
			appointmentRepository.save(appointment);

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


		// start
		List<Appointment> tempList = null;
		if (role.equals("D")) {
			tempList = appointmentRepository.findAllByEmployeeIdAndAppointmentDateBetweenOrderByAppointmentDate(userId,
					currentLocalDateTime, maxLocalDateTime);
		} else if (role.equals("N")) {
			tempList = appointmentRepository.findAllByAppointmentDateBetweenOrderByAppointmentDate(currentLocalDateTime,
					maxLocalDateTime);
		} else {
			tempList = appointmentRepository.findAllByPatientIdAndAppointmentDateBetweenOrderByAppointmentDate(userId,
					currentLocalDateTime, maxLocalDateTime);
		}


		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();

		for (Appointment appointment : tempList) {
			if (!appointment.isAccepted()) {
				continue;
			}
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
			tempObj.setAccepted(appointment.isAccepted());
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

	public static List<Long> callGetListApi(String url, String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		List<Long> idList = new ArrayList<Long>();
		try {
			ResponseEntity<List<Long>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Long>>() {
					});
			return response.getBody();
		} catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
		}
		return null;

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

	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByPatient(String name, String authToken) {
		String url = "http://localhost:8080/user/patientsearch/" + name;
		List<Long> idList = callGetListApi(url, authToken);
		String url_claims = "http://localhost:8088/extract_claims";
		String claims = getClaimForToken(authToken, url_claims);
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		LocalDateTime maxLocalDateTime = currentLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		List<Appointment> tempList = null;
		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();
		for (long id : idList) {
//			tempList = appointmentRepository.findAllByPatientIdAndAppointmentDateBetweenOrderByAppointmentDate(id,
//					currentLocalDateTime, maxLocalDateTime);
			tempList = appointmentRepository.findAllByPatientId(id);
			for (Appointment appointment : tempList) {
				AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
				tempObj.setDate(appointment.getAppointmentDate());
				tempObj.setDescription(appointment.getDescription());
				tempObj.setTime(appointment.getAppointmentTime());
				tempObj.setTime(slotRepository.findById(appointment.getSlotId()).get().getSlots());
				tempObj.setAppointmentId(appointment.getAppointmentId());
				String response = callGetApi("http://localhost:8080/user/employee/name/" + appointment.getEmployeeId(),
						authToken);
				tempObj.setPhysician(response);
				Set<EditHistory> editHistorySet = appointment.getEditHistory();
				tempObj.setEditHistory(appointment.getEditHistory());
				String patientName = callGetApi("http://localhost:8080/user/patient/name/" + appointment.getPatientId(),
						authToken);
				tempObj.setTitle("Appointment with " + patientName);
				tempObj.setAccepted(appointment.isAccepted());
				tempObj.setTitle(appointment.getMeetingTitle());
				responseList.add(tempObj);

			}
			tempList.clear();
		}

		return responseList;
	}

	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByPhysician(String name, String authToken) {
		String url = "http://localhost:8080/user/physiciansearch/" + name;
		List<Long> idList = callGetListApi(url, authToken);
		String url_claims = "http://localhost:8088/extract_claims";
		String claims = getClaimForToken(authToken, url_claims);
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		LocalDateTime maxLocalDateTime = currentLocalDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		List<Appointment> tempList = null;
		List<AppointmentEmployeeResponseDTO> responseList = new LinkedList<>();
		for (long id : idList) {
			tempList = appointmentRepository.findAllByEmployeeId(id);
			for (Appointment appointment : tempList) {
				AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
				tempObj.setDate(appointment.getAppointmentDate());
				tempObj.setDescription(appointment.getDescription());
				tempObj.setTime(appointment.getAppointmentTime());
				tempObj.setTime(slotRepository.findById(appointment.getSlotId()).get().getSlots());
				tempObj.setAppointmentId(appointment.getAppointmentId());
				String response = callGetApi("http://localhost:8080/user/employee/name/" + appointment.getEmployeeId(),
						authToken);
				tempObj.setPhysician(response);
				Set<EditHistory> editHistorySet = appointment.getEditHistory();
				tempObj.setEditHistory(appointment.getEditHistory());
				String patientName = callGetApi("http://localhost:8080/user/patient/name/" + appointment.getPatientId(),
						authToken);
				tempObj.setTitle("Appointment with " + patientName);
				tempObj.setAccepted(appointment.isAccepted());
				tempObj.setPatientId(appointment.getPatientId());
				tempObj.setTitle(appointment.getMeetingTitle());
				responseList.add(tempObj);

			}
			tempList.clear();
		}

		return responseList;
	}

	public String declineAppointment(long appointmentId, String token) {
		// decline appointment and send email notification
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		if (appointment.isPresent()) {
			Appointment appointmentObj = appointment.get();
			appointmentObj.setAccepted(false);
			appointmentRepository.save(appointmentObj);
//			String url_claims = "http://localhost:8088/extract_claims";
//			String claims = getClaimForToken(token, url_claims);
//			String email = claims.split(",")[2].trim();
			String email = callGetApi("http://localhost:8080/user/patient/email/" + appointment.get().getPatientId(),
					token);
			sendAppointmentDeclinedEmail(email, appointmentObj, token);
			return "appointment declined";
		}
		return "Error deleting appoitnemnt";
	}

	public void sendAppointmentDeclinedEmail(String to, Appointment appointment, String token) {
		Optional<Slot> slot = slotRepository.findById(appointment.getSlotId());
		if (slot.isPresent()) {
			EmailDTO emailObj = new EmailDTO();
			emailObj.setTo(to);
			emailObj.setSubject("Appointment declined");
			String physicianName = callGetApi("http://localhost:8080/user/employee/name/" + appointment.getEmployeeId(),
					token);
			emailObj.setBody("Your appointment declined for the date " + appointment.getAppointmentDate() + " "
					+ slot.get().getSlots() + " and doctor was " + physicianName);

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			// ExtractRequest request = new ExtractRequest();
			// request.setToken(token);
			String url = "http://localhost:8080/notification/email";
			ResponseEntity<String> response = restTemplate.postForEntity(url, emailObj, String.class);
			System.out.println(response.getBody());
		}
	}
	
	public  List<Long> getSlotIdByEmployeeIdAndAppointmentDate(String employeeIdAndDate){
		String[] names = employeeIdAndDate.trim().split("\\s+");
		long employeeId = 0;
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		if (names.length > 1) {
			employeeId = Long.parseLong(names[0]);
			startDate = LocalDateTime.parse(names[1].concat("T00:01:00"));
			endDate = LocalDateTime.parse(names[1].concat("T23:59:00"));
		} 

		List<Long> tempList = new ArrayList<>();
		List<Appointment> responseList = appointmentRepository.findAllByAppointmentDateBetweenAndEmployeeIdOrderByAppointmentDate(startDate, endDate, employeeId);
		
		for (Appointment appointment : responseList) {
			tempList.add(appointment.getSlotId());
		}
		
		return tempList;
	}
	
	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByEmployeeId(long employeeId){
		List<AppointmentEmployeeResponseDTO> tempList = new ArrayList<>();
		List<Appointment> responseList = appointmentRepository.findAppointmentByEmployeeId(employeeId);
		
		for (Appointment appointment : responseList) {
			AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
			tempObj.setAppointmentId(appointment.getAppointmentId());
			tempObj.setMeetingTitle(appointment.getMeetingTitle());
			tempObj.setEmployeeId(appointment.getEmployeeId());
			tempObj.setSlot(appointment.getSlotId()+"");
			tempObj.setDate(appointment.getAppointmentDate());
			tempObj.setDescription(appointment.getDescription());
			tempObj.setTime(appointment.getAppointmentTime());
			tempObj.setTime(slotRepository.findById(appointment.getSlotId()).get().getSlots());
			tempObj.setAppointmentId(appointment.getAppointmentId());
			tempObj.setAccepted(appointment.isAccepted());
			tempObj.setTitle(appointment.getMeetingTitle());
			tempList.add(tempObj);

		}
		
		return tempList;
	}
	
	public List<AppointmentEmployeeResponseDTO> searchAppointmentsByAppointmentId(long id){
		List<AppointmentEmployeeResponseDTO> tempList = new ArrayList<>();
		Optional<Appointment> responseList = appointmentRepository.findById(id);
		
		if(responseList.isPresent()) {
			Appointment appointment = responseList.get();
			AppointmentEmployeeResponseDTO tempObj = new AppointmentEmployeeResponseDTO();
			tempObj.setAppointmentId(appointment.getAppointmentId());
			tempObj.setMeetingTitle(appointment.getMeetingTitle());
			tempObj.setEmployeeId(appointment.getEmployeeId());
			tempObj.setSlot(appointment.getSlotId()+"");
			tempObj.setDate(appointment.getAppointmentDate());
			tempObj.setDescription(appointment.getDescription());
			tempObj.setTime(appointment.getAppointmentTime());
			//tempObj.setTime(slotRepository.findById(appointment.getSlotId()).get().getSlots());
			tempObj.setAppointmentId(appointment.getAppointmentId());
			tempObj.setAccepted(appointment.isAccepted());
			tempObj.setTitle(appointment.getMeetingTitle());
			tempObj.setPatientId(appointment.getPatientId());
			tempList.add(tempObj);

		}
		
		return tempList;
	}


}
