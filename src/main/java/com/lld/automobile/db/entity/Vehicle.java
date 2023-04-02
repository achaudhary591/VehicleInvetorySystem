package com.lld.automobile.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Column
	@Id
	private String id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinTable(name = "stationToVehicles",
			   joinColumns = { @JoinColumn(name = "vehicleId") }, 
			   inverseJoinColumns = { @JoinColumn(name = "vehicleStationId") }
	)
	private VehicleStation vehicleStation;

	public Vehicle(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
