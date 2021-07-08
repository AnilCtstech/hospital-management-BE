package com.citiustech.hms.inboxmanagement.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
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

	public List<AppointmentEmployeeResponseDTO> getWeekAppointments() {
		LocalDate localDate = LocalDate.now();
		java.sql.Date currentDate = java.sql.Date.valueOf(localDate);
		// java.sql.Date date1 = java.sql.Date.valueOf(LocalDate.now());
		LocalDate finalLocalDate = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		java.sql.Date finalDate = java.sql.Date.valueOf(finalLocalDate);

		List<Appointment> findAllByAppointmentDateBetween = appointmentRepository
				.findAllByAppointmentDateBetween(currentDate, finalDate);
//		List<Appointment> tempList = appointmentRepository.findAllByAppointmentDateBetweenAndSortByAppointmentDate(currentDate, finalDate);
//		System.out.println("LOG");
		return null;
	}

	/*
	 * List<AppointmentEmployeeResponseDTO> getWeekAppointments() throws
	 * ParseException { List<AppointmentEmployeeResponseDTO> responseList = new
	 * ArrayList<>();
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 * Date currentDate = new Date();
	 * System.out.println(formatter.format(currentDate));
	 * 
	 * LocalDate nextSaturday =
	 * LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)); Date
	 * finalDate = java.sql.Date.valueOf(nextSaturday);
	 * 
	 * List<Appointment> appointmentList = appointmentRepository.findAll(); for
	 * (Appointment appointment : appointmentList) { Date appointmentDate = new
	 * SimpleDateFormat("dd/MM/yyyy").parse(appointment.getAppointmentDate()); if
	 * (isDateInBetweenIncludingEndPoints(currentDate, finalDate, appointmentDate))
	 * { AppointmentEmployeeResponseDTO response = new
	 * AppointmentEmployeeResponseDTO();
	 * response.setDate(appointment.getAppointmentDate());
	 * response.setDescription(appointment.getDescription());
	 * response.setTime(appointment.getAppointmentTime());
	 * responseList.add(response); } }
	 * 
	 * Collections.sort(responseList, new
	 * Comparator<AppointmentEmployeeResponseDTO>() { public int
	 * compare(AppointmentEmployeeResponseDTO p1, AppointmentEmployeeResponseDTO p2)
	 * { return p1.getDate().compareTo(p2.getDate()); } });
	 * 
	 * Collections.sort(responseList, new
	 * Comparator<AppointmentEmployeeResponseDTO>() { public int
	 * compare(AppointmentEmployeeResponseDTO p1, AppointmentEmployeeResponseDTO p2)
	 * { return p1.getTime().compareTo(p2.getTime()); } });
	 * 
	 * return responseList; }
	 * 
	 * public static boolean isDateInBetweenIncludingEndPoints(final Date min, final
	 * Date max, final Date date) { return !(date.before(min) || date.after(max)); }
	 */

}
