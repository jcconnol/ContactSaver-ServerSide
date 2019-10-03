package com.cont.commands.users;

import java.util.UUID;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.api.User;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserQuery implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		UserEntity userEntity = this.userRepository.get(this.userId);
		if (userEntity != null) {
			return new User(userEntity);
		} else {
			throw new NotFoundException("User");
		}
	}

	//Properties
	private UUID userId;
	public UUID getUserId() {
		return this.userId;
	}
	public UserQuery setUserId(UUID userId) {
		this.userId = userId;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getProductRepository() {
		return this.userRepository;
	}
	public UserQuery setProductRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserQuery() {
		this.userRepository = new UserRepository();
	}
}
