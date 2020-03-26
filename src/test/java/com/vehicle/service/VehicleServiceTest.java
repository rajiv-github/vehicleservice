package com.vehicle.service;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.vehicle.model.Vehicle;
import com.vehicle.repository.VehicleRepository;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
	
	@InjectMocks
	VehicleService vehicleService;
	
	@Mock
	VehicleRepository vehicleRepository;
	
	 @Test
	 public void testFindTheGreatestFromAllData() throws InterruptedException, ExecutionException {
		 Vehicle vehicle = new Vehicle();
			vehicle.setVin("1A4AABBC5KD501999");
		 Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(vehicle);
	     assertEquals("1A4AABBC5KD501999", vehicleService.createVehicle(vehicle).get().getVin());
	 }
	
}
