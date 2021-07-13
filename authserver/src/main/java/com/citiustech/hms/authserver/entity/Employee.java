package com.citiustech.hms.authserver.entity;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.citiustech.hms.authserver.service.RoleConverter;

@Entity
public class Employee {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	@NotNull
	private String title;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String password;
	@NotNull
	@Email(message = "Email should be valid")
	private String email;
	@NotNull
	private Date dateOfBirth;
	 
	//private String role;
	
	@Convert(converter = RoleConverter.class)
	private Role role;
	
	private int passCount;
	
	public Employee() {
		super();
	}
	public Employee(@NotNull Long employeeId, @NotNull String title, @NotNull String firstName,
			@NotNull String lastName, @NotNull String password,
			@NotNull @Email(message = "Email should be valid") String email, @NotNull Date dateOfBirth, Role role,int passCount) {
		super();
		this.employeeId = employeeId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.passCount = passCount;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
 
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getPassCount() {
		return passCount;
	}
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	
	
	

}
