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
		UserEntity UserEntity = this.UserRepository.get(this.UserId);
		if (UserEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("User");
		}
		
		UserEntity.delete();
	}

	//Properties
	private UUID UserId;
	public UUID getUserId() {
		return this.UserId;
	}
	public UserDeleteCommand setUserId(UUID UserId) {
		this.UserId = UserId;
		return this;
	}
	
	private UserRepositoryInterface UserRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.UserRepository;
	}
	public UserDeleteCommand setUserRepository(UserRepositoryInterface UserRepository) {
		this.UserRepository = UserRepository;
		return this;
	}
	
	public UserDeleteCommand() {
		this.UserRepository = new UserRepository();
	}
}
