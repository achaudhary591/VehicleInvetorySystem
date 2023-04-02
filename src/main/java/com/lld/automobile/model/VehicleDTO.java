package com.lld.automobile.model;

import lombok.Builder;

@Builder
public class VehicleDTO {

	public String id;
	public String name;
	public VehicleStationDTO vehicleStationDTO;

}
