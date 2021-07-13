package com.citiustech.hms.UserRegisterManagement.dto;

import com.citiustech.hms.UserRegisterManagement.entity.Role;

public class Profile {

	public Profile() {
	}

	public Profile(String firstName, String lastName, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}


	
	
}
