package com.cont.contacts.users;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.ConflictException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.User;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserCreateCommand implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		//Validations
		if (StringUtils.isBlank(this.apiUser.getFirstName())) {
			throw new UnprocessableEntityException("first name");
		}
		if (StringUtils.isBlank(this.apiUser.getLastName())) {
			throw new UnprocessableEntityException("last name");
		}
		if (StringUtils.isBlank(this.apiUser.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
		
		//Generate a numeric employee ID of length EMPLOYEE_ID_LENGTH for the new employee,
		// making sure that the employee ID is not already assigned to another employee.
		// This field is distinct from the record ID.
		
		if(StringUtils.isBlank(this.apiUser.getUserId())) {
			throw new UnprocessableEntityException("userid");
		}
		
		if(this.userRepository.userIdExists(this.apiUser.getUserId())) {
			throw new ConflictException("user id nonunique");
		}
		
		/*
		String newEmployeeId;
		do {
			newEmployeeId = RandomStringUtils.randomNumeric(EMPLOYEE_ID_LENGTH);
		} while (this.employeeRepository.employeeIdExists(newEmployeeId));*/

		this.apiUser.setUserId(this.apiUser.getUserId());

		UserEntity userEntity = new UserEntity(this.apiUser); //Create a new ENTITY object from the API object details.
		userEntity.save(); //Write, via an INSERT, the new record to the database.
		
		this.apiUser.setUserId(userEntity.getUserId()); //Synchronize information generated by the database upon INSERT.
		this.apiUser.setCreatedOn(userEntity.getCreatedOn());
		this.apiUser.setPassword(StringUtils.EMPTY); //Only send the password over the network when modifying the database.
		
		return this.apiUser;
	}
	
	//Properties
	private User apiUser;
	public User getApiUser() {
		return this.apiUser;
	}
	public UserCreateCommand setApiUser(User apiUser) {
		this.apiUser = apiUser;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.userRepository;
	}
	public UserCreateCommand setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
		
	public UserCreateCommand() {
		this.userRepository = new UserRepository();
	}
}
