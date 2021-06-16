package com.citiustech.hms.UserRegisterManagement.dto;

public class ChangePasswordDto {

	private String oldPassword;
	private String newPassword;
	
	public ChangePasswordDto() {
		super();
	}
	public ChangePasswordDto(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
