package com.vehicle.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vehicle.model.Vehicle;
import com.vehicle.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Async("asyncExecutor")	 
	public CompletableFuture<Vehicle> createVehicle(Vehicle vehicleObj) {
		
		vehicleObj.setId(UUID.randomUUID().toString());
		
		Vehicle newVehicle = vehicleRepository.save(vehicleObj);

		return CompletableFuture.completedFuture(newVehicle);
	}
}
