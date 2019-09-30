package com.cont.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.models.entities.UserEntity;

public class User {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public User setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private String userId;
	public String getUserId() {
		return this.userId;
	}
	public User setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public User setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	public User() {
		this.id = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.userId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}
	
	public User(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.password = StringUtils.EMPTY;
		this.lastName = userEntity.getLastName();
		this.createdOn = userEntity.getCreatedOn();
		this.firstName = userEntity.getFirstName();
		this.userId = userEntity.getUserId();
	}
}
