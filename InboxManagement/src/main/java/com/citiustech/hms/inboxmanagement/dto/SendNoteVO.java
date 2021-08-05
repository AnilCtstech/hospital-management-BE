package com.citiustech.hms.inboxmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendNoteVO {

	public SendNoteVO(long id, String message, boolean urgency, long fromEmployeeId, long toEmployeeId, Role role) {
		this.id = id;
		this.message = message;
		this.urgency = urgency;
		this.fromEmployeeId = fromEmployeeId;
		this.toEmployeeId = toEmployeeId;
		this.role = role;
	}

	public SendNoteVO() {
	}

	private long id;

	private String message;

	private boolean urgency;

	private long fromEmployeeId;

	private long toEmployeeId;

	private Role role;

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

	@Override
	public String toString() {
		return "SendNoteVO [id=" + id + ", message=" + message + ", urgency=" + urgency + ", fromEmployeeId="
				+ fromEmployeeId + ", toEmployeeId=" + toEmployeeId + ", role=" + role + "]";
	}

}
