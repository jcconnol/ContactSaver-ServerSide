package edu.uark.models.api;

import org.apache.commons.lang3.StringUtils;

public class EmployeeLogin {
	private String employeeUsername;
	public String getEmployeeUsername() {
		return this.employeeUsername;
	}
	public EmployeeLogin setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
		return this;
	}
	private String password;
	public String getPassword() {
		return this.password;
	}
	public EmployeeLogin setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public EmployeeLogin() {
		this.password = StringUtils.EMPTY;
		this.employeeUsername = StringUtils.EMPTY;
	}
}
