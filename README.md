# vehicleservice

# Spring Boot Asyncrhonous RESTful 

## Description
This project contains the REST API Implementation which Implement asyncrhonous REST API Endpoint using Springboot.

The main goal of the project is to show how to write a Async @RestController, Error handling and Logging Cross cutting in Spring Boot and include documentation with Swagger.

## Technical Stack:

:Spring Boot
:Maven 
:STS 
:Java 8
:Packaging (JAR)
:JPA
:RESTful
:H2
:JUnit
:Mockito

## How to Run 

This application is packaged as a jar which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/VehicleService-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run 
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2020-03-25 23:06:06.272  INFO 113240 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-03-25 23:06:06.275  INFO 113240 --- [           main] com.vehicle.VehicleServiceApplication    : Started VehicleServiceApplication in 6.557 seconds (JVM running for 7.852)
```

## How does it work?
You can use the endpoints behind http://localhost:8080/vehicle-api/vehicle to create a new one, you need to perform a POST operation.

Actually, much better if you just start the application and navigate to http://localhost:8080/swagger-ui.html. There you'll find a nice API documentation thanks to Swagger. Moreover, you can play with it.

# Explanation

REST API Implementation
Implemented asyncrhonous REST API Endpoint using Async Executors in Service as follows

```
  @Async("asyncExecutor")	 
	public CompletableFuture<Vehicle> createVehicle(Vehicle vehicleObj) {
		
		vehicleObj.setId(UUID.randomUUID().toString());
		
		Vehicle newVehicle = vehicleRepository.save(vehicleObj);

		return CompletableFuture.completedFuture(newVehicle);
	}
```     
## Success use case:
- Valid payload should be printed in the console log from the method
```
2020-03-25 23:13:43.941 DEBUG 79428 --- [nio-8080-exec-1] o.s.w.f.CommonsRequestLoggingFilter      : After request [POST /vehicle-api/vehicle, client=0:0:0:0:0:0:0:1, headers=[host:"localhost:8080", connection:"keep-alive", content-length:"121", cache-control:"no-cache", sec-fetch-dest:"empty", user-agent:"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36", postman-token:"5390e01f-7e49-4949-fdb7-f0a72f831357", accept:"*/*", origin:"chrome-extension://fhbjgbiflinjbdggehcddcbncdddomop", sec-fetch-site:"none", sec-fetch-mode:"cors", accept-encoding:"gzip, deflate, br", accept-language:"en-US,en;q=0.9,te;q=0.8", Content-Type:"application/json;charset=UTF-8"], payload={
  "vin": "1A4AABBC5KD501999",
  "year": 2019,
  "make": "FCA",
  "model": "RAM",
  "transmissionType": "MANUAL"
}]
```

## Error Handling

Implemented validation for "transmissionType" which MUST accept only "MANUAL" or "AUTO" in the request body.

Invalid payload implemented using Controller Advice:
- Invalid payload MUST be thrown with a corresponding error code and error message in the response body
- Any other error MUST throw 500 "Internal Server Error" http response code with some info message in the response body

```
@ControllerAdvice
public class ApplicationControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
	public @ResponseBody ServiceResponse handleResourceNotFound(final Exception  exception,
			final HttpServletRequest request) {

		ServiceResponse error = new ServiceResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());

		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ServiceResponse handleException(final Exception exception,
			final HttpServletRequest request) {

		ServiceResponse error = new ServiceResponse();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return error;
	}

}
        
 ```


## Logging
Logging Cross-cutting concern

Placed logging before and after method using AOP.
```
2020-03-25 23:13:43.920  INFO 79428 --- [ AsynchThread-1] com.vehicle.aspect.LoggingAspect         : Started calling CrudRepository.save
2020-03-25 23:13:43.920  INFO 79428 --- [ AsynchThread-1] com.vehicle.aspect.LoggingAspect         : Execution time of CrudRepository.save :: 76 ms
2020-03-25 23:13:43.921  INFO 79428 --- [ AsynchThread-1] com.vehicle.aspect.LoggingAspect         : Started calling VehicleService.createVehicle
2020-03-25 23:13:43.921  INFO 79428 --- [ AsynchThread-1] com.vehicle.aspect.LoggingAspect         : Execution time of VehicleService.createVehicle :: 80 ms
2020-03-25 23:13:43.922  INFO 79428 --- [nio-8080-exec-1] com.vehicle.aspect.LoggingAspect         : Started calling VehicleEndpoint.createVehicle
2020-03-25 23:13:43.922  INFO 79428 --- [nio-8080-exec-1] com.vehicle.aspect.LoggingAspect         : Execution time of VehicleEndpoint.createVehicle :: 88 ms

```
