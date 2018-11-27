package com.cont.commands.employees;

import org.apache.commons.lang3.StringUtils;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.UnauthorizedException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Employee;
import com.cont.models.api.EmployeeLogin;
import com.cont.models.entities.EmployeeEntity;
import com.cont.models.repositories.EmployeeRepository;
import com.cont.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeLoginCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		//Validations
		if (StringUtils.isBlank(this.employeeLogin.getEmployeeId())) {
			throw new UnprocessableEntityException("employee ID");
		}
		
		EmployeeEntity employeeEntity = this.employeeRepository.byEmployeeId(this.employeeLogin.getEmployeeId());
		if ((employeeEntity == null) || !employeeEntity.getPassword().equals(EmployeeEntity.hashPassword(this.employeeLogin.getPassword()))) {
			throw new UnauthorizedException();
		}

		return new Employee(employeeEntity);
	}

	//Properties
	private EmployeeLogin employeeLogin;
	public EmployeeLogin getEmployeeLogin() {
		return this.employeeLogin;
	}
	public EmployeeLoginCommand setEmployeeLogin(EmployeeLogin employeeLogin) {
		this.employeeLogin = employeeLogin;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getProductRepository() {
		return this.employeeRepository;
	}
	public EmployeeLoginCommand setProductRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeLoginCommand() {
		this.employeeLogin = new EmployeeLogin();
		this.employeeRepository = new EmployeeRepository();
	}
}
