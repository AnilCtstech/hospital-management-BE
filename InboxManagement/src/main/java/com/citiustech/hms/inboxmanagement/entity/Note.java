package com.citiustech.hms.inboxmanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.citiustech.hms.inboxmanagement.dto.Role;

@Entity
public class Note {

	public Note() {
	}

	public Note(String message, boolean urgency, long fromEmployeeId, long toEmployeeId, LocalDateTime dateTime,
			String responseMessage, LocalDateTime responseDateTime, boolean status) {
		this.message = message;
		this.urgency = urgency;
		this.fromEmployeeId = fromEmployeeId;
		this.toEmployeeId = toEmployeeId;
		this.dateTime = dateTime;
		this.responseMessage = responseMessage;
		this.responseDateTime = responseDateTime;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", message=" + message + ", urgency=" + urgency + ", fromEmployeeId=" + fromEmployeeId
				+ ", toEmployeeId=" + toEmployeeId + ", dateTime=" + dateTime + ", responseMessage=" + responseMessage
				+ ", responseDateTime=" + responseDateTime + ", status=" + status + "]";
	}
	
	
	
}
