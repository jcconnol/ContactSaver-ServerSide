package com.John.models.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.John.dataaccess.entities.BaseEntity;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.models.api.User;
import com.John.models.entities.fieldnames.UserFieldNames;
import com.John.models.enums.UserClassification;

public class UserEntity extends BaseEntity<UserEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.active = rs.getBoolean(UserFieldNames.ACTIVE);
		this.password = rs.getString(UserFieldNames.PASSWORD);
		this.lastName = rs.getString(UserFieldNames.LAST_NAME);
		this.firstName = rs.getString(UserFieldNames.FIRST_NAME);
		this.userId = rs.getString(UserFieldNames.USER_ID);
		this.managerId = ((UUID) rs.getObject(UserFieldNames.MANAGER_ID));
		this.classification = UserClassification.map(rs.getInt(UserFieldNames.CLASSIFICATION));
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(UserFieldNames.ACTIVE, this.active);
		record.put(UserFieldNames.PASSWORD, this.password);
		record.put(UserFieldNames.LAST_NAME, this.lastName);
		record.put(UserFieldNames.FIRST_NAME, this.firstName);
		record.put(UserFieldNames.MANAGER_ID, this.managerId);
		record.put(UserFieldNames.USER_ID, this.userId);
		record.put(UserFieldNames.CLASSIFICATION, this.classification.getValue());

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

	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public UserEntity setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			this.propertyChanged(UserFieldNames.ACTIVE);
		}
		
		return this;
	}

	private UserClassification classification;
	public UserClassification getClassification() {
		return this.classification;
	}
	public UserEntity setClassification(UserClassification classification) {
		if (this.classification != classification) {
			this.classification = classification;
			this.propertyChanged(UserFieldNames.CLASSIFICATION);
		}
		
		return this;
	}

	private UUID managerId;
	public UUID getManagerId() {
		return this.managerId;
	}
	public UserEntity setManagerId(UUID managerId) {
		if (!this.managerId.equals(managerId)) {
			this.managerId = managerId;
			this.propertyChanged(UserFieldNames.MANAGER_ID);
		}
		
		return this;
	}
	
	public User synchronize(User apiUser) {
		this.setActive(apiUser.getActive());
		this.setLastName(apiUser.getLastName());
		this.setFirstName(apiUser.getFirstName());
		this.setManagerId(apiUser.getManagerId());
		this.setClassification(UserClassification.map(apiUser.getClassification()));
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
		
		this.active = false;
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.userId = StringUtils.EMPTY;
		this.classification = UserClassification.NOT_DEFINED;
	}

	public UserEntity(User apiUser) {
		super(DatabaseTable.USER);
		
		this.active = apiUser.getActive();
		this.lastName = apiUser.getLastName();
		this.firstName = apiUser.getFirstName();
		this.managerId = apiUser.getManagerId();
		this.userId = apiUser.getUserId();
		this.classification = UserClassification.map(apiUser.getClassification());
		this.password = UserEntity.hashPassword(
			apiUser.getPassword());
	}
}
