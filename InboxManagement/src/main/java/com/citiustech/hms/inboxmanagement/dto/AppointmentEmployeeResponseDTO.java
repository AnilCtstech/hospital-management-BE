package com.citiustech.hms.inboxmanagement.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.citiustech.hms.inboxmanagement.entity.EditHistory;

public class AppointmentEmployeeResponseDTO {

	private long appointmentId;
	private String title;
	private String description;
	private LocalDateTime date;
	private String time;
	private String physician;
	private String slot;
	private String meetingTitle;
	private Set<EditHistory> editHistory;
	private boolean isAccepted;

	public Set<EditHistory> getEditHistory() {
		return editHistory;
	}

	public void setEditHistory(Set<EditHistory> editHistory) {
		this.editHistory = editHistory;
	}

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

	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

}
