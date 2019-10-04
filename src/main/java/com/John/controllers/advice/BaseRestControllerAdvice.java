package com.John.controllers.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.John.controllers.exceptions.ConflictException;
import com.John.controllers.exceptions.NotFoundException;
import com.John.controllers.exceptions.UnauthorizedException;
import com.John.controllers.exceptions.UnprocessableEntityException;

@ControllerAdvice
public class BaseRestControllerAdvice {
	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	VndErrors notFoundExceptionHandler(UnauthorizedException e) {
		return new VndErrors("error", e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors notFoundExceptionHandler(NotFoundException e) {
		return new VndErrors("error", e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors conflictExceptionHandler(ConflictException e) {
		return new VndErrors("error", e.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(UnprocessableEntityException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	VndErrors unprocessableEntityExceptionHandler(UnprocessableEntityException e) {
		return new VndErrors("error", e.getMessage());
	}
}
