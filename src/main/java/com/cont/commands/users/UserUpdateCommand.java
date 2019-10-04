package com.cont.commands.users;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.User;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserUpdateCommand implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		//Validations
		if (StringUtils.isBlank(this.apiUser.getFirstName())) {
			throw new UnprocessableEntityException("first name");
		}
		if (StringUtils.isBlank(this.apiUser.getLastName())) {
			throw new UnprocessableEntityException("last name");
		}

		UserEntity UserEntity = this.UserRepository.get(this.UserId);
		if (UserEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("User");
		}
		
		this.apiUser = UserEntity.synchronize(this.apiUser); //Synchronize any incoming changes for UPDATE to the database.
		
		UserEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiUser;
	}
	
	//Properties
	private UUID UserId;
	public UUID getUserId() {
		return this.UserId;
	}
	public UserUpdateCommand setUserId(UUID UserId) {
		this.UserId = UserId;
		return this;
	}
	
	private User apiUser;
	public User getApiUser() {
		return this.apiUser;
	}
	public UserUpdateCommand setApiUser(User apiUser) {
		this.apiUser = apiUser;
		return this;
	}
	
	private UserRepositoryInterface UserRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.UserRepository;
	}
	public UserUpdateCommand setUserRepository(UserRepositoryInterface UserRepository) {
		this.UserRepository = UserRepository;
		return this;
	}
	
	public UserUpdateCommand() {
		this.UserRepository = new UserRepository();
	}
}
