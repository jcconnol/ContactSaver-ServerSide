package com.cont.contacts.users;

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
		UserEntity userEntity = this.userRepository.get(this.userName);
		if (userEntity != null) {
			return new User(userEntity);
		} else {
			throw new NotFoundException("User");
		}
	}

	//Properties
	private UUID userName;
	public UUID getUserName() {
		return this.userName;
	}
	public UserQuery setUserName(UUID userName) {
		this.userName = userName;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.userRepository;
	}
	public UserQuery setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserQuery() {
		this.userRepository = new UserRepository();
	}
}
