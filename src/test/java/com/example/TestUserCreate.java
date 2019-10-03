package com.example;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cont.commands.users.UserCreateCommand;
import com.cont.models.api.User;

public class TestUserCreate {
	
	private User user;
	private UserCreateCommand userCreate;

	@Before
	public void setUp() throws Exception {
		user.setFirstName("LOL");
		user.setLastName("LOLZ");
		user.setPassword("pass");
		
		userCreate = new UserCreateCommand();
		userCreate.setApiUser(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		userCreate.execute();
	}

}
