package com.lld.automobile.model;

import org.springframework.lang.NonNull;

public class VehicleIssueRequest {

	@NonNull
	public String userId;

	@NonNull
	public String vehicleId;
}
