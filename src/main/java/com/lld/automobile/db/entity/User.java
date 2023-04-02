package com.lld.automobile.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true, length = 8)
	private String otp;

	@Column(nullable = true)
	private LocalDateTime otpGeneratedDateTime;

	@OneToOne
	@JoinTable(name = "userVehicle",
			   joinColumns = { @JoinColumn(name = "userId") }, 
			   inverseJoinColumns = { @JoinColumn(name = "vehicleId") }
	)
	private Vehicle userVehicle;

	@Column(nullable = false)
	private boolean isAdmin;

}
