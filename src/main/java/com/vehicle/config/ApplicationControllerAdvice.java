package com.vehicle.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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