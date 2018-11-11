package edu.uark.commands.employees;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeCreateCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		//Validations
		if (StringUtils.isBlank(this.apiEmployee.getFirstName())) {
			throw new UnprocessableEntityException("first name");
		}
		if (StringUtils.isBlank(this.apiEmployee.getLastName())) {
			throw new UnprocessableEntityException("last name");
		}
		if (StringUtils.isBlank(this.apiEmployee.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
		
		//Generate a numeric employee ID of length EMPLOYEE_ID_LENGTH for the new employee,
		// making sure that the employee ID is not already assigned to another employee.
		// This field is distinct from the record ID.
		
		if(StringUtils.isBlank(this.apiEmployee.getEmployeeId())) {
			throw new UnprocessableEntityException("employeeid");
		}
		
		if(this.employeeRepository.employeeIdExists(this.apiEmployee.getEmployeeId())) {
			throw new UnprocessableEntityException("employee id nonunique");
		}
		
		/*
		String newEmployeeId;
		do {
			newEmployeeId = RandomStringUtils.randomNumeric(EMPLOYEE_ID_LENGTH);
		} while (this.employeeRepository.employeeIdExists(newEmployeeId));*/

		this.apiEmployee.setEmployeeId(this.apiEmployee.getEmployeeId());

		EmployeeEntity employeeEntity = new EmployeeEntity(this.apiEmployee); //Create a new ENTITY object from the API object details.
		employeeEntity.save(); //Write, via an INSERT, the new record to the database.
		
		this.apiEmployee.setId(employeeEntity.getId()); //Synchronize information generated by the database upon INSERT.
		this.apiEmployee.setCreatedOn(employeeEntity.getCreatedOn());
		this.apiEmployee.setPassword(StringUtils.EMPTY); //Only send the password over the network when modifying the database.
		
		return this.apiEmployee;
	}
	
	//Properties
	private Employee apiEmployee;
	public Employee getApiEmployee() {
		return this.apiEmployee;
	}
	public EmployeeCreateCommand setApiEmployee(Employee apiEmployee) {
		this.apiEmployee = apiEmployee;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getEmployeeRepository() {
		return this.employeeRepository;
	}
	public EmployeeCreateCommand setEmployeeRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	private static final int EMPLOYEE_ID_LENGTH = 4;
	
	public EmployeeCreateCommand() {
		this.employeeRepository = new EmployeeRepository();
	}
}
