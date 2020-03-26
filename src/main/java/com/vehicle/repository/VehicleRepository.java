package com.vehicle.repository;

import org.springframework.data.repository.CrudRepository;

import com.vehicle.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

}
