package com.cont.models.api;

import org.apache.commons.lang3.StringUtils;

public class UserLogin {
	private String userId;
	public String getUserId() {
		return this.userId;
	}
	public UserLogin setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	private String password;
	public String getPassword() {
		return this.password;
	}
	public UserLogin setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserLogin() {
		this.password = StringUtils.EMPTY;
		this.userId = StringUtils.EMPTY;
	}
}
