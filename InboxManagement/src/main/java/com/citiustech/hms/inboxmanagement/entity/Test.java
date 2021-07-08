package com.citiustech.hms.inboxmanagement.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {

	@Id
	private long id;

	private LocalDateTime eventTime;
	
	private Timestamp eventTimeStamp;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getEventTimeStamp() {
		return eventTimeStamp;
	}

	public void setEventTimeStamp(Timestamp eventTimeStamp) {
		this.eventTimeStamp = eventTimeStamp;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}

}
