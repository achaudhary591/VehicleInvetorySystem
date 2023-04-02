package com.lld.automobile.model;

import java.util.List;

import lombok.Builder;

@Builder
public class VehicleStationDTO {

	public String id;
	public String name;
	public List<VehicleDTO> vehicles;

	public VehicleStationDTO(String id, String name, List<VehicleDTO> vehicles) {
		super();
		this.id = id;
		this.name = name;
		this.vehicles = vehicles;
	}

}
