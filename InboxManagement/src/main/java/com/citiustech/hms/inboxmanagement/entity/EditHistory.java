package com.citiustech.hms.inboxmanagement.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EditHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalTime timeOfEdit;
	private String employeeDetail;
	private String reason;
	@Column(length = 3048)
	private String previousAppintmentDetails;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "appointment_id", nullable = false)
	private Appointment appointment;

	public EditHistory() {
	}

	public EditHistory(LocalTime timeOfEdit, String employeeDetail, String reason, Appointment appointment) {
		super();
		this.timeOfEdit = timeOfEdit;
		this.employeeDetail = employeeDetail;
		this.reason = reason;
		this.appointment = appointment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getTimeOfEdit() {
		return timeOfEdit;
	}

	public void setTimeOfEdit(LocalTime timeOfEdit) {
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

	public String getPreviousAppintmentDetails() {
		return previousAppintmentDetails;
	}

	public void setPreviousAppintmentDetails(String previousAppintmentDetails) {
		this.previousAppintmentDetails = previousAppintmentDetails;
	}

	@Override
	public String toString() {
		return "EditHistory [id=" + id + ", timeOfEdit=" + timeOfEdit + ", employeeDetail=" + employeeDetail
				+ ", reason=" + reason + ", previousAppintmentDetails=" + previousAppintmentDetails + ", appointment="
				+ appointment + "]";
	}

}
