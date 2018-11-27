package com.cont.commands.employees;

import java.util.UUID;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.api.Employee;
import com.cont.models.entities.EmployeeEntity;
import com.cont.models.repositories.EmployeeRepository;
import com.cont.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeQuery implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		EmployeeEntity employeeEntity = this.employeeRepository.get(this.employeeId);
		if (employeeEntity != null) {
			return new Employee(employeeEntity);
		} else {
			throw new NotFoundException("Employee");
		}
	}

	//Properties
	private UUID employeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeQuery setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getProductRepository() {
		return this.employeeRepository;
	}
	public EmployeeQuery setProductRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeQuery() {
		this.employeeRepository = new EmployeeRepository();
	}
}
