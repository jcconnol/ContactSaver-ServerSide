package com.cont.contacts.users;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.UnauthorizedException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.User;
import com.cont.models.api.UserLogin;
import com.cont.models.entities.UserEntity;
import com.cont.models.repositories.UserRepository;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserLoginCommand implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		//Validations
		if (StringUtils.isBlank(this.userLogin.getUserId())) {
			throw new UnprocessableEntityException("User ID");
		}
		
		UserEntity userEntity = this.userRepository.byUserId(this.userLogin.getUserId());
		if ((userEntity == null) || !userEntity.getPassword().equals(UserEntity.hashPassword(this.userLogin.getPassword()))) {
			throw new UnauthorizedException();
		}

		return new User(userEntity);
	}

	//Properties
	private UserLogin userLogin;
	public UserLogin getUserLogin() {
		return this.userLogin;
	}
	public UserLoginCommand setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getUserRepository() {
		return this.userRepository;
	}
	public UserLoginCommand setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserLoginCommand() {
		this.userLogin = new UserLogin();
		this.userRepository = new UserRepository();
	}
}
