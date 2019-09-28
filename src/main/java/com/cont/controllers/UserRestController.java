package com.cont.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cont.contacts.users.UserCreateCommand;
import com.cont.contacts.users.UserDeleteCommand;
import com.cont.contacts.users.UserLoginCommand;
import com.cont.contacts.users.UserQuery;
import com.cont.contacts.users.UserUpdateCommand;
import com.cont.models.api.User;
import com.cont.models.api.UserLogin;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable UUID userId) {
		return (new UserQuery()).
			setUserId(userId).
			execute();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return (new UserCreateCommand()).
			setApiUser(user).
			execute();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public User updateEmployee(@PathVariable UUID userId, @RequestBody User user) {
		return (new UserUpdateCommand()).
			setUserId(userId).
			setApiUser(user).
			execute();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable UUID userId) {
		(new UserDeleteCommand()).
			setUserId(userId).
			execute();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User userLogin(@RequestBody UserLogin userLogin) {
		return (new UserLoginCommand()).
			setUserLogin(userLogin).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (UserRestController)";
	}
}
