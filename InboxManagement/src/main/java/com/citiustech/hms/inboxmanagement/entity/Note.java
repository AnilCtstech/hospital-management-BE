package com.citiustech.hms.inboxmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.citiustech.hms.inboxmanagement.dto.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Note {

	public Note(long id, String message, boolean urgency, long fromEmployeeId, long toEmployeeId, Role role,
			LocalDateTime dateTime) {
		this.id = id;
		this.message = message;
		this.urgency = urgency;
		this.fromEmployeeId = fromEmployeeId;
		this.toEmployeeId = toEmployeeId;
		this.role = role;
		this.dateTime = dateTime;
	}

	public Note() {
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 1024)
	private String message;

	private boolean urgency;

	private long fromEmployeeId;

	private long toEmployeeId;

	private Role role;

	@CreationTimestamp
	private LocalDateTime dateTime;
	
	@OneToMany(mappedBy = "note")
	@JsonManagedReference
	private List<NoteResponse> noteResponse;
	
	public List<NoteResponse> getNoteResponse() {
		return noteResponse;
	}

	public void setNoteResponse(List<NoteResponse> noteResponse) {
		this.noteResponse = noteResponse;
	}


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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", message=" + message + ", urgency=" + urgency + ", fromEmployeeId=" + fromEmployeeId
				+ ", toEmployeeId=" + toEmployeeId + ", role=" + role + ", dateTime=" + dateTime + "]";
	}


}
