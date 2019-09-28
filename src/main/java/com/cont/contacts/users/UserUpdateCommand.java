package com.cont.contacts.users;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
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

		UserEntity userEntity = this.userRepository.get(this.userId);
		if (userEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("User");
		}
		
		this.apiUser = userEntity.synchronize(this.apiUser); //Synchronize any incoming changes for UPDATE to the database.
		
		userEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiUser;
	}
	
	//Properties
	private UUID userId;
	public UUID getUserId() {
		return this.userId;
	}
	public UserUpdateCommand setUserId(UUID userId) {
		this.userId = userId;
		return this;
	}
	
	private User apiUser;
	public User getApiUser() {
		return this.apiUser;
	}
	public UserUpdateCommand setApiUser(User apiUser) {
		this.apiUser= apiUser;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.userRepository;
	}
	public UserUpdateCommand setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserUpdateCommand() {
		this.userRepository = new UserRepository();
	}
}
