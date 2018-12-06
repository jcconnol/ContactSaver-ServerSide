package com.cont.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.models.entities.EmployeeEntity;
import com.cont.models.enums.EmployeeClassification;

public class Employee {
	private String employeeId;
	public String getEmployeeId() {
		return this.employeeId;
	}
	public Employee setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public Employee setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public Employee setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public Employee setPassword(String password) {
		this.password = password;
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Employee setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	public Employee() {
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}
	
	public Employee(EmployeeEntity employeeEntity) {
		this.password = StringUtils.EMPTY;
		this.lastName = employeeEntity.getLastName();
		this.createdOn = employeeEntity.getCreatedOn();
		this.firstName = employeeEntity.getFirstName();
		this.employeeId = employeeEntity.getEmployeeId();
	}
}
