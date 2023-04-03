package com.lld.automobile.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.automobile.db.entity.User;
import com.lld.automobile.db.entity.Vehicle;
import com.lld.automobile.db.entity.VehicleStation;
import com.lld.automobile.db.repository.UserRepository;
import com.lld.automobile.db.repository.VehicleRepository;
import com.lld.automobile.db.repository.VehicleStationRepository;
import com.lld.automobile.model.UserDTO;
import com.lld.automobile.model.VehicleDTO;
import com.lld.automobile.model.VehicleIssueRequest;
import com.lld.automobile.model.VehicleStationDTO;
import com.lld.automobile.model.VehicleStationRequest;

@Service
public class VehicleInventoryService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleStationRepository vehicleStationRepository;

	@Autowired
	private UserRepository userRepository;

	public VehicleDTO getVehicleById(String id) throws Exception {
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
		if (vehicleOptional.isEmpty()) {
			throw new Exception("vehicle not found");
		}
		Vehicle vehicle = vehicleOptional.get();
		return VehicleDTO
				.builder().id(vehicle.getId()).name(vehicle.getName()).vehicleStationDTO(VehicleStationDTO.builder()
						.id(vehicle.getVehicleStation().getId()).name(vehicle.getVehicleStation().getName()).build())
				.build();
	}

	public VehicleStationDTO getStationById(String id) throws Exception {
		Optional<VehicleStation> vehicleStationOptional = vehicleStationRepository.findById(id);
		if (vehicleStationOptional.isEmpty()) {
			throw new Exception("vehicle station not found");
		}
		VehicleStation vehicleStation = vehicleStationOptional.get();
		return VehicleStationDTO.builder().id(vehicleStation.getId()).name(vehicleStation.getName())
				.vehicles(
						vehicleStation.getVehicles().stream().map(v -> mapToVehicleDto(v)).collect(Collectors.toList()))
				.build();
	}

	public UserDTO getUserById(String id) throws Exception {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new Exception("user not found");
		}
		User user = userOptional.get();
		return UserDTO.builder().id(user.getId()).email(user.getEmail()).name(user.getName())
				.vehicle(mapToVehicleDto(user.getUserVehicle())).build();
	}


	public VehicleStationDTO createVehicleStation(VehicleStationRequest vehicleStationRequest) {
		VehicleStation vehicleStation = VehicleStation.builder().id(generateUUIDString())
				.name(vehicleStationRequest.name.get()).build();
		vehicleStationRepository.save(vehicleStation);
		return VehicleStationDTO.builder().id(vehicleStation.getId()).name(vehicleStation.getName()).build();
	}

	public VehicleStationDTO assignVehicleToStation(VehicleStationRequest request) throws Exception {
		Optional<VehicleStation> vehicleStationOptional = vehicleStationRepository.findById(request.id.get());

		if (vehicleStationOptional.isEmpty()) {
			throw new Exception("making change to station, which d.n.e.");
		}

		List<Vehicle> vehicleToAdd = vehicleRepository
				.findAllById(request.vehicles.stream().map(v -> v.id).collect(Collectors.toList()));
		if (vehicleToAdd.isEmpty()) {
			throw new Exception("Vehicles cannot be found");
		}

		VehicleStation vehicleStation = vehicleStationOptional.get();
		vehicleToAdd.forEach(v -> vehicleStation.getVehicles().add(v));
		vehicleStationRepository.save(vehicleStation);
		return VehicleStationDTO.builder().id(vehicleStation.getId()).name(vehicleStation.getName())
				.vehicles(
						vehicleStation.getVehicles().stream().map(v -> mapToVehicleDto(v)).collect(Collectors.toList()))
				.build();
	}

	public UserDTO issueVehicleToUser(VehicleIssueRequest request) throws Exception {
		Optional<User> userOptional = userRepository.findById(request.userId);
		if (userOptional.isEmpty()) {
			throw new Exception("user not found");
		}
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(request.vehicleId);
		if (vehicleOptional.isEmpty()) {
			throw new Exception("Vehicles cannot be found");
		}
		User user = userOptional.get();
		user.setUserVehicle(vehicleOptional.get());
		userRepository.save(user);
		return UserDTO.builder().id(user.getId()).email(user.getEmail()).name(user.getName())
				.vehicle(mapToVehicleDto(user.getUserVehicle())).build();
	}

	public UserDTO returnVehicle(VehicleIssueRequest request) throws Exception {
		Optional<User> userOptional = userRepository.findById(request.userId);
		if (userOptional.isEmpty()) {
			throw new Exception("user not found");
		}
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(request.vehicleId);
		if (vehicleOptional.isEmpty()) {
			throw new Exception("Vehicles cannot be found");
		}
		User user = userOptional.get();
		user.setUserVehicle(null);
		userRepository.save(user);
		return UserDTO.builder().id(user.getId()).email(user.getEmail()).name(user.getName()).build();
	}

	private String generateUUIDString() {
		return UUID.randomUUID().toString();
	}

	private VehicleDTO mapToVehicleDto(Vehicle vehicle) {
		return VehicleDTO.builder().id(vehicle.getId()).name(vehicle.getName()).build();
	}

}
