package com.citiustech.UserRegisterManagement.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Role role;
	
	
	

}
