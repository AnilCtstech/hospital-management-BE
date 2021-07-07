package com.citiustech.hms.inboxmanagement.dto;

public class AppointmentEmployeeResponseDTO {

	private String title;
	private String description;
	private String date;
	private String time;
	private EditHisotry EditHistory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public EditHisotry getEditHistory() {
		return EditHistory;
	}

	public void setEditHistory(EditHisotry editHistory) {
		EditHistory = editHistory;
	}

}
