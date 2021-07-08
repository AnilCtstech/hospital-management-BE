package com.citiustech.hms.inboxmanagement.dto;

import java.time.LocalDateTime;

public class AppointmentEmployeeResponseDTO {

	private long appointmentId;
	private String title;
	private String description;
	private LocalDateTime date;
	private String time;
	private EditHisotry EditHistory;

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public EditHisotry getEditHistory() {
		return EditHistory;
	}

	public void setEditHistory(EditHisotry editHistory) {
		EditHistory = editHistory;
	}

}
