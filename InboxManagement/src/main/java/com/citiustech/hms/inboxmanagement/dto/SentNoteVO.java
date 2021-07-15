package com.citiustech.hms.inboxmanagement.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SentNoteVO {

	public SentNoteVO() {
	}

	public SentNoteVO(long id, String message, boolean urgency, long fromEmployeeId, long toEmployeeId, Role role,
			LocalDateTime dateTime, String responseMessage, LocalDateTime responseDateTime, boolean status,
			String toEmployee, String fromEmployee) {
		this.id = id;
		this.message = message;
		this.urgency = urgency;
		this.fromEmployeeId = fromEmployeeId;
		this.toEmployeeId = toEmployeeId;
		this.role = role;
		this.dateTime = dateTime;
		this.responseMessage = responseMessage;
		this.responseDateTime = responseDateTime;
		this.status = status;
		this.toEmployee = toEmployee;
		this.fromEmployee = fromEmployee;
	}

	private long id;
	
	private String message;
	
	private boolean urgency;
	
	private long fromEmployeeId;
	
	private long toEmployeeId;
	
	private Role role;
	
	@CreationTimestamp
	private LocalDateTime dateTime;
	
	private String responseMessage;
	
	@UpdateTimestamp
	private LocalDateTime responseDateTime;
	
	private boolean status;
	
	private String toEmployee;
	
	private String fromEmployee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isUrgency() {
		return urgency;
	}

	public void setUrgency(boolean urgency) {
		this.urgency = urgency;
	}

	public long getFromEmployeeId() {
		return fromEmployeeId;
	}

	public void setFromEmployeeId(long fromEmployeeId) {
		this.fromEmployeeId = fromEmployeeId;
	}

	public long getToEmployeeId() {
		return toEmployeeId;
	}

	public void setToEmployeeId(long toEmployeeId) {
		this.toEmployeeId = toEmployeeId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public LocalDateTime getResponseDateTime() {
		return responseDateTime;
	}

	public void setResponseDateTime(LocalDateTime responseDateTime) {
		this.responseDateTime = responseDateTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getToEmployee() {
		return toEmployee;
	}

	public void setToEmployee(String toEmployee) {
		this.toEmployee = toEmployee;
	}

	public String getFromEmployee() {
		return fromEmployee;
	}

	public void setFromEmployee(String fromEmployee) {
		this.fromEmployee = fromEmployee;
	}

	@Override
	public String toString() {
		return "SentNoteVO [id=" + id + ", message=" + message + ", urgency=" + urgency + ", fromEmployeeId="
				+ fromEmployeeId + ", toEmployeeId=" + toEmployeeId + ", role=" + role + ", dateTime=" + dateTime
				+ ", responseMessage=" + responseMessage + ", responseDateTime=" + responseDateTime + ", status="
				+ status + ", toEmployee=" + toEmployee + ", fromEmployee=" + fromEmployee + "]";
	}
	
	

}
