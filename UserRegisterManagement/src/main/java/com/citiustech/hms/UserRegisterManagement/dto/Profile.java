package com.citiustech.hms.UserRegisterManagement.dto;

import com.citiustech.hms.UserRegisterManagement.entity.Role;

public class Profile {

	public Profile() {
	}

	public Profile(Long id, String firstName, String lastName, String dateOfBirth, String email,
			Boolean isActive, Boolean isBlocked, Role role, String isAccess) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.role = role;
		this.status = status;
	}

	private Long id;

	private String firstName;

	private String lastName;
	
	private String dateOfBirth;
	
	private String email;
	
	private String status;

	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long long1) {
		this.id = long1;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", status=" + status + ", role=" + role + "]";
	}
	

}
