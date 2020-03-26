package com.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
    private String id;
	private String vin;
	private String year;
	private String make;
	private String model;
	@Enumerated(EnumType.ORDINAL)
    private VehicleTransmissionTypeEnum transmissionType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public VehicleTransmissionTypeEnum getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(VehicleTransmissionTypeEnum transmissionType) {
		this.transmissionType = transmissionType;
	}

	@Override
	public String toString() {
		return "[vin=" + vin + ", year=" + year + ", make=" + make + ", model=" + model + ", transmissionType="
				+ transmissionType + "]";
	}

}
