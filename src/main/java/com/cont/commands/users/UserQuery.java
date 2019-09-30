package com.cont.commands.users;

import java.util.UUID;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.api.User;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserQuery implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		UserEntity UserEntity = this.UserRepository.get(this.UserId);
		if (UserEntity != null) {
			return new User(UserEntity);
		} else {
			throw new NotFoundException("User");
		}
	}

	//Properties
	private UUID UserId;
	public UUID getUserId() {
		return this.UserId;
	}
	public UserQuery setUserId(UUID UserId) {
		this.UserId = UserId;
		return this;
	}
	
	private UserRepositoryInterface UserRepository;
	public UserRepositoryInterface getProductRepository() {
		return this.UserRepository;
	}
	public UserQuery setProductRepository(UserRepositoryInterface UserRepository) {
		this.UserRepository = UserRepository;
		return this;
	}
	
	public UserQuery() {
		this.UserRepository = new UserRepository();
	}
}
