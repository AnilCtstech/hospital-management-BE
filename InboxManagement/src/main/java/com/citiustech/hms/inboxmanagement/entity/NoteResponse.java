package com.citiustech.hms.inboxmanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class NoteResponse {

	public NoteResponse() {
	}

	public NoteResponse(long id, String responseMessage, LocalDateTime responseDateTime, boolean status) {
		this.id = id;
		this.responseMessage = responseMessage;
		this.responseDateTime = responseDateTime;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 1024)
	private String responseMessage;
	
	@CreationTimestamp
	private LocalDateTime responseDateTime;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="note")
	@JsonBackReference
	private Note note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "NoteResponse [id=" + id + ", responseMessage=" + responseMessage + ", responseDateTime="
				+ responseDateTime + ", status=" + status + "]";
	}

	
}
