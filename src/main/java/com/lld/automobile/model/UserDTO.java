package com.lld.automobile.model;

import lombok.Builder;

@Builder
public class UserDTO {

	public String id;

	public String email;

	public String name;

	public VehicleDTO vehicle;
}
