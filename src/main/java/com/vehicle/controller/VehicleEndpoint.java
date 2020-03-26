package com.vehicle.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.model.Vehicle;
import com.vehicle.service.VehicleService;

@RequestMapping(value = "/vehicle-api")
@RestController
public class VehicleEndpoint {

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) throws Exception {

		CompletableFuture<Vehicle> vehicleObje = vehicleService.createVehicle(vehicle);
		return ResponseEntity.ok("vehicle id ::"+vehicleObje.get().getId());
	}

}
