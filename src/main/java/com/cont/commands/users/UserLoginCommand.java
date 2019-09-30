package com.cont.commands.users;

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
		if (StringUtils.isBlank(this.UserLogin.getUserId())) {
			throw new UnprocessableEntityException("User ID");
		}
		
		UserEntity UserEntity = this.UserRepository.byUserId(this.UserLogin.getUserId());
		if ((UserEntity == null) || !UserEntity.getPassword().equals(UserEntity.hashPassword(this.UserLogin.getPassword()))) {
			throw new UnauthorizedException();
		}

		return new User(UserEntity);
	}

	//Properties
	private UserLogin UserLogin;
	public UserLogin getUserLogin() {
		return this.UserLogin;
	}
	public UserLoginCommand setUserLogin(UserLogin UserLogin) {
		this.UserLogin = UserLogin;
		return this;
	}
	
	private UserRepositoryInterface UserRepository;
	public UserRepositoryInterface getProductRepository() {
		return this.UserRepository;
	}
	public UserLoginCommand setProductRepository(UserRepositoryInterface UserRepository) {
		this.UserRepository = UserRepository;
		return this;
	}
	
	public UserLoginCommand() {
		this.UserLogin = new UserLogin();
		this.UserRepository = new UserRepository();
	}
}
