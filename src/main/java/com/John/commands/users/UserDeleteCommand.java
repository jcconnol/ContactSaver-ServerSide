package com.John.commands.users;

import java.util.UUID;

import com.John.commands.VoidCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.models.entities.UserEntity;
import com.John.models.repositories.UserRepository;
import com.John.models.repositories.interfaces.UserRepositoryInterface;

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
