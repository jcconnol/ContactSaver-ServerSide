package com.John.commands.users;

import org.apache.commons.lang3.StringUtils;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.UnauthorizedException;
import com.John.controllers.exceptions.UnprocessableEntityException;
import com.John.models.api.User;
import com.John.models.api.UserLogin;
import com.John.models.entities.UserEntity;
import com.John.models.repositories.UserRepository;
import com.John.models.repositories.interfaces.UserRepositoryInterface;

public class UserLoginCommand implements ResultCommandInterface<User> {
	@Override
	public User execute() {
		//Validations
		if (StringUtils.isBlank(this.userLogin.getUserId())) {
			throw new UnprocessableEntityException("user ID");
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
	public UserRepositoryInterface getProductRepository() {
		return this.userRepository;
	}
	public UserLoginCommand setProductRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public UserLoginCommand() {
		this.userLogin = new UserLogin();
		this.userRepository = new UserRepository();
	}
}
