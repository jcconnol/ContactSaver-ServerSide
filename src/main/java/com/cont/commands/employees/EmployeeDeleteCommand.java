package com.cont.commands.employees;

import java.util.UUID;

import com.cont.commands.VoidCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.entities.EmployeeEntity;
import com.cont.models.repositories.EmployeeRepository;
import com.cont.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		EmployeeEntity employeeEntity = this.employeeRepository.get(this.employeeId);
		if (employeeEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Employee");
		}
		
		employeeEntity.delete();
	}

	//Properties
	private UUID employeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeDeleteCommand setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getEmployeeRepository() {
		return this.employeeRepository;
	}
	public EmployeeDeleteCommand setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeDeleteCommand() {
		this.employeeRepository = new EmployeeRepository();
	}
}
