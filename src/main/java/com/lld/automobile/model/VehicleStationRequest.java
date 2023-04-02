package com.lld.automobile.model;

import java.util.List;
import java.util.Optional;

public class VehicleStationRequest {

	public Optional<String> id;
	public Optional<String> name;
	public List<VehicleDTO> vehicles;
	// add more attributes
}
