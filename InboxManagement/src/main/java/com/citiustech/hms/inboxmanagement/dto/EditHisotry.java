package com.citiustech.hms.inboxmanagement.dto;

public class EditHisotry {

	private String editTime;
	private String userDetails;
	private String reasonOfModification;
	private String appointmentDetails;

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

	public String getAppointmentDetails() {
		return appointmentDetails;
	}

	public void setAppointmentDetails(String appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}

}
