package com.cont.contacts.users;

import java.util.UUID;

import com.cont.contacts.VoidCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		UserEntity userEntity = this.userRepository.get(this.userId);
		if (userEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("User");
		}
		
		userEntity.delete();
	}

	//Properties
	private UUID userId;
	public UUID getUserId() {
		return this.userId;
	}
	public UserDeleteCommand setUserId(UUID userId) {
		this.userId = userId;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.userRepository;
	}
	public UserDeleteCommand setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserDeleteCommand() {
		this.userRepository = new UserRepository();
	}
}
