package com.lld.automobile.db.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "vehicleStations")
public class VehicleStation {

	@Column
	@Id
	private String id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "vehicleStation")
	private Set<Vehicle> vehicles;

	public VehicleStation(String id, String name, Set<Vehicle> vehicles) {
		super();
		this.id = id;
		this.name = name;
		this.vehicles = vehicles;
	}

}
