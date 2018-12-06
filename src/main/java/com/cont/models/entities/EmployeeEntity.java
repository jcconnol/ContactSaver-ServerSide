package com.cont.models.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.dataaccess.entities.BaseEntity;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.models.api.Employee;
import com.cont.models.entities.fieldnames.EmployeeFieldNames;
import com.cont.models.enums.EmployeeClassification;

public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.password = rs.getString(EmployeeFieldNames.PASSWORD);
		this.lastName = rs.getString(EmployeeFieldNames.LAST_NAME);
		this.firstName = rs.getString(EmployeeFieldNames.FIRST_NAME);
		this.employeeId = rs.getString(EmployeeFieldNames.EMPLOYEE_ID);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(EmployeeFieldNames.PASSWORD, this.password);
		record.put(EmployeeFieldNames.LAST_NAME, this.lastName);
		record.put(EmployeeFieldNames.FIRST_NAME, this.firstName);
		record.put(EmployeeFieldNames.EMPLOYEE_ID, this.employeeId);

		return record;
	}

	private String employeeId;
	public String getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeEntity setEmployeeId(String employeeId) {
		if (!StringUtils.equals(this.employeeId, employeeId)) {
			this.employeeId = employeeId;
			this.propertyChanged(EmployeeFieldNames.EMPLOYEE_ID);
		}
		
		return this;
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public EmployeeEntity setFirstName(String firstName) {
		if (!StringUtils.equals(this.firstName, firstName)) {
			this.firstName = firstName;
			this.propertyChanged(EmployeeFieldNames.FIRST_NAME);
		}
		
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public EmployeeEntity setLastName(String lastName) {
		if (!StringUtils.equals(this.lastName, lastName)) {
			this.lastName = lastName;
			this.propertyChanged(EmployeeFieldNames.LAST_NAME);
		}
		
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public EmployeeEntity setPassword(String password) {
		if (!StringUtils.equals(this.password, password)) {
			this.password = password;
			this.propertyChanged(EmployeeFieldNames.PASSWORD);
		}
		
		return this;
	}
	
	public Employee synchronize(Employee apiEmployee) {
		this.setLastName(apiEmployee.getLastName());
		this.setFirstName(apiEmployee.getFirstName());
		if (!StringUtils.isBlank(apiEmployee.getPassword())) {
			this.setPassword(
				EmployeeEntity.hashPassword(
					apiEmployee.getPassword()));
		}
		
		apiEmployee.setEmployeeId(this.getEmployeeId());
		apiEmployee.setPassword(StringUtils.EMPTY); //Only send the password over the network when modifying the database.
		apiEmployee.setEmployeeId(this.employeeId); //The employee ID may not be changed from a client.
		apiEmployee.setCreatedOn(this.getCreatedOn());
		
		return apiEmployee;
	}
	
	public static String hashPassword(String password) {
		String hashedPassword;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			hashedPassword = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			hashedPassword = StringUtils.EMPTY;
		}
		
		return hashedPassword;
	}
	
	public EmployeeEntity() {
		super(DatabaseTable.EMPLOYEE);
		
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
	}

	public EmployeeEntity(Employee apiEmployee) {
		super(DatabaseTable.EMPLOYEE);
		this.lastName = apiEmployee.getLastName();
		this.firstName = apiEmployee.getFirstName();
		this.employeeId = apiEmployee.getEmployeeId();
		this.password = EmployeeEntity.hashPassword(
			apiEmployee.getPassword());
	}
}
