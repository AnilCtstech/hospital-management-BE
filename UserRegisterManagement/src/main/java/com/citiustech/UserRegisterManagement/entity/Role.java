package com.citiustech.UserRegisterManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
public class Role {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String roleType;
	@OneToOne
	@MapsId
	@JoinColumn(name="user_id")
	private Employee employee;
}
