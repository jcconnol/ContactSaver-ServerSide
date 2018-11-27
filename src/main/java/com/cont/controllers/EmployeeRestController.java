package com.cont.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cont.commands.employees.ActiveEmployeeCountsQuery;
import com.cont.commands.employees.EmployeeCreateCommand;
import com.cont.commands.employees.EmployeeDeleteCommand;
import com.cont.commands.employees.EmployeeLoginCommand;
import com.cont.commands.employees.EmployeeQuery;
import com.cont.commands.employees.EmployeeUpdateCommand;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.ActiveEmployeeCounts;
import com.cont.models.api.Employee;
import com.cont.models.api.EmployeeLogin;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeRestController {
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable UUID employeeId) {
		return (new EmployeeQuery()).
			setEmployeeId(employeeId).
			execute();
	}
	
	@RequestMapping(value = "/activecounts/{classification}", method = RequestMethod.GET)
	public ActiveEmployeeCounts getActiveEmployeeCounts(@PathVariable int classification) {
		return (new ActiveEmployeeCountsQuery()).
				setEmployeeClassification(classification)
				.execute();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee) {
		return (new EmployeeCreateCommand()).
			setApiEmployee(employee).
			execute();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody Employee employee) {
		return (new EmployeeUpdateCommand()).
			setEmployeeId(employeeId).
			setApiEmployee(employee).
			execute();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable UUID employeeId) {
		(new EmployeeDeleteCommand()).
			setEmployeeId(employeeId).
			execute();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Employee employeeLogin(@RequestBody EmployeeLogin employeeLogin) {
		return (new EmployeeLoginCommand()).
			setEmployeeLogin(employeeLogin).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (EmployeeRestController)";
	}
}
