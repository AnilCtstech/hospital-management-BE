package com.citiustech.hms.inboxmanagement.dto;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable

public class EditHisotry {

	private String editTime;
	private String userDetails;
	private String reasonOfModification;
	private String previousAppointmentDetails;

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(String userDetails) {
		this.userDetails = userDetails;
	}

	public String getReasonOfModification() {
		return reasonOfModification;
	}

	public void setReasonOfModification(String reasonOfModification) {
		this.reasonOfModification = reasonOfModification;
	}

	public String getPreviousAppointmentDetails() {
		return previousAppointmentDetails;
	}

	public void setPreviousAppointmentDetails(String previousAppointmentDetails) {
		this.previousAppointmentDetails = previousAppointmentDetails;
	}

}
