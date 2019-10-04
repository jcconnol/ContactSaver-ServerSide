package com.John.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.John.models.entities.UserEntity;
import com.John.models.enums.UserClassification;

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

	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public User setActive(boolean active) {
		this.active = active;
		return this;
	}

	private int classification;
	public int getClassification() {
		return this.classification;
	}
	public User setClassification(int classification) {
		this.classification = classification;
		return this;
	}

	private UUID managerId;
	public UUID getManagerId() {
		return this.managerId;
	}
	public User setManagerId(UUID managerId) {
		this.managerId = managerId;
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
		this.active = false;
		this.id = new UUID(0, 0);
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.userId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
		this.classification = UserClassification.NOT_DEFINED.getValue();
	}
	
	public User(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.password = StringUtils.EMPTY;
		this.active = userEntity.getActive();
		this.lastName = userEntity.getLastName();
		this.createdOn = userEntity.getCreatedOn();
		this.firstName = userEntity.getFirstName();
		this.managerId = userEntity.getManagerId();
		this.userId = userEntity.getUserId();
		this.classification = userEntity.getClassification().getValue();
	}
}
