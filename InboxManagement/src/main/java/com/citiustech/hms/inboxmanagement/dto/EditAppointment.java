package com.citiustech.hms.inboxmanagement.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.citiustech.hms.inboxmanagement.entity.EditHistory;

public class EditAppointment {

	private long appointmentId;
	private String description;
	private LocalDateTime appointmentDate;
	private String appointmentTime;
	private long employeeId;
	private long patientId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String createdBy;
	private String updatedBy;

	// fields for edit history
	private Time timeOfEdit;
	private String employeeDetail;
	private String reason;

	private long slotId;
	private String meetingTitle;

	public Time getTimeOfEdit() {
		return timeOfEdit;
	}

	public void setTimeOfEdit(Time timeOfEdit) {
		this.timeOfEdit = timeOfEdit;
	}

	public String getEmployeeDetail() {
		return employeeDetail;
	}

	public void setEmployeeDetail(String employeeDetail) {
		this.employeeDetail = employeeDetail;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

}
