package com.vehicle.controller;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vehicle.model.Vehicle;
import com.vehicle.model.VehicleTransmissionTypeEnum;
import com.vehicle.service.VehicleService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VehicleEndpoint.class)
public class VehicleEndpointTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehicleService VehicleService;


	static ExecutorService exe = null;
	static CompletableFuture<Vehicle> exeFutureList = null;
	
	@Test
	public void createVehicle() throws Exception {
		
		exe = Executors.newFixedThreadPool(1);
        
        
		String postVehicleJson = "{\r\n" + 
				"  \"vin\": \"1A4AABBC5KD501999\",\r\n" + 
				"  \"year\": 2019,\r\n" + 
				"  \"make\": \"FCA\",\r\n" + 
				"  \"model\": \"RAM\",\r\n" + 
				"  \"transmissionType\": \"MANUAL\"\r\n" + 
				"}";

		
		Vehicle vehicle = new Vehicle();
		vehicle.setVin("1A4AABBC5KD501999");
		vehicle.setTransmissionType(VehicleTransmissionTypeEnum.MANUAL);
		CompletableFuture<Vehicle> f = CompletableFuture.supplyAsync(() -> {
		    return vehicle;
		}, exe);
		Mockito.when(VehicleService.createVehicle(Mockito.any(Vehicle.class))).thenReturn(f);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/vehicle-api/vehicle")
				.accept(MediaType.APPLICATION_JSON).content(postVehicleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
}
