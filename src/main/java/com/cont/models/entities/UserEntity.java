package com.cont.models.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cont.dataaccess.entities.BaseEntity;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.models.api.User;
import com.cont.models.entities.fieldnames.UserFieldNames;

public class UserEntity extends BaseEntity<UserEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.password = rs.getString(UserFieldNames.PASSWORD);
		this.lastName = rs.getString(UserFieldNames.LAST_NAME);
		this.firstName = rs.getString(UserFieldNames.FIRST_NAME);
		this.userId = rs.getString(UserFieldNames.USER_ID);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(UserFieldNames.PASSWORD, this.password);
		record.put(UserFieldNames.LAST_NAME, this.lastName);
		record.put(UserFieldNames.FIRST_NAME, this.firstName);
		record.put(UserFieldNames.USER_ID, this.userId);

		return record;
	}

	private String userId;
	public String getUserId() {
		return this.userId;
	}
	public UserEntity setUserId(String userId) {
		if (!StringUtils.equals(this.userId, userId)) {
			this.userId = userId;
			this.propertyChanged(UserFieldNames.USER_ID);
		}
		
		return this;
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public UserEntity setFirstName(String firstName) {
		if (!StringUtils.equals(this.firstName, firstName)) {
			this.firstName = firstName;
			this.propertyChanged(UserFieldNames.FIRST_NAME);
		}
		
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public UserEntity setLastName(String lastName) {
		if (!StringUtils.equals(this.lastName, lastName)) {
			this.lastName = lastName;
			this.propertyChanged(UserFieldNames.LAST_NAME);
		}
		
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public UserEntity setPassword(String password) {
		if (!StringUtils.equals(this.password, password)) {
			this.password = password;
			this.propertyChanged(UserFieldNames.PASSWORD);
		}
		
		return this;
	}
	
	public User synchronize(User apiUser) {
		this.setLastName(apiUser.getLastName());
		this.setFirstName(apiUser.getFirstName());
		if (!StringUtils.isBlank(apiUser.getPassword())) {
			this.setPassword(
				UserEntity.hashPassword(
					apiUser.getPassword()));
		}
		
		apiUser.setId(this.getId());
		apiUser.setPassword(StringUtils.EMPTY); //Only send the password over the network when modifying the database.
		apiUser.setUserId(this.userId); //The user ID may not be changed from a client.
		apiUser.setCreatedOn(this.getCreatedOn());
		
		return apiUser;
	}
	
	public static String hashPassword(String password) {
		String hashedPassword;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			hashedPassword = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			hashedPassword = StringUtils.EMPTY;
		}
		
		return hashedPassword;
	}
	
	public UserEntity() {
		super(DatabaseTable.USER);
		
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.userId = StringUtils.EMPTY;
	}

	public UserEntity(User apiUser) {
		super(DatabaseTable.USER);
		
		this.lastName = apiUser.getLastName();
		this.firstName = apiUser.getFirstName();
		this.userId = apiUser.getUserId();
		this.password = UserEntity.hashPassword(
			apiUser.getPassword());
	}
}
