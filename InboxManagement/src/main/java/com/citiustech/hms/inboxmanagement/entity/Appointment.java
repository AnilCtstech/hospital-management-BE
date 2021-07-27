package com.citiustech.hms.inboxmanagement.entity;

import java.io.Serializable; 
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Appointment implements Serializable {
	// primary
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long appointmentId;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDateTime appointmentDate;

	@Column(nullable = false)
	private String appointmentTime;

	// foriegn
	@Column(nullable = false)
	private long employeeId;

	// foriegn
	@Column(nullable = false)
	private long patientId;

//	@Column(nullable = false)
	private Timestamp createdAt;

//	@Column(nullable = false)
	private Timestamp updatedAt;

//	@Column(nullable = false)
	private String createdBy;

//	@Column(nullable = false)
	private String updatedBy;

	@OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EditHistory> editHistory;

	@Column(nullable = false)
	private long slotId;

	private String meetingTitle;

	private boolean isAccepted = true;

	public Appointment() {
		super();
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

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Set<EditHistory> getEditHistory() {
		return editHistory;
	}

	public void setEditHistory(Set<EditHistory> editHistory) {
		this.editHistory = editHistory;
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

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public String toString() {
		return "Appointment [" + " description=" + description + ", appointmentDate=" + appointmentDate
				+ ", appointmentTime=" + appointmentTime + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", slotId=" + slotId + ", meetingTitle="
				+ meetingTitle + ", isAccepted=" + isAccepted + "]";
	}

}
