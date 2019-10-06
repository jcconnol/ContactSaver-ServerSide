package com.John.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.John.dataaccess.entities.BaseEntity;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.models.api.Contact;
import com.John.models.entities.fieldnames.ContactFieldNames;

public class ContactEntity extends BaseEntity<ContactEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.name = rs.getString(ContactFieldNames.NAME);
		this.number = rs.getInt(ContactFieldNames.NUMBER);
		this.ownerId = rs.getString(ContactFieldNames.OWNER_ID);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.NAME, this.name);
		record.put(ContactFieldNames.NUMBER, this.number);
		record.put(ContactFieldNames.OWNER_ID, this.ownerId);
		
		return record;
	}
	
	private String ownerId;
	public String getOwnerId() {
		return this.ownerId;
	}
	public ContactEntity setOwnerId(String ownerId) {
		if (!StringUtils.equals(this.ownerId, ownerId)) {
			this.ownerId = ownerId;
			this.propertyChanged(ContactFieldNames.OWNER_ID);
		}
		return this;
	} 
	
	private String name;
	public String getName() {
		return this.name;
	}
	public ContactEntity setName(String name) {
		if (!StringUtils.equals(this.name, name)) {
			this.name = name;
			this.propertyChanged(ContactFieldNames.NAME);
		}
		
		return this;
	}

	private int number;
	public int getNumber() {
		return this.number;
	}
	public ContactEntity setNumber(int number) {
		if (this.number != number) {
			this.number = number;
			this.propertyChanged(ContactFieldNames.NUMBER);
		}
		
		return this;
	}
	
	public Contact synchronize(Contact apiContact) {
		this.setNumber(apiContact.getNumber());
		this.setName(apiContact.getName());
		
		apiContact.setOwnerId(this.getOwnerId());
		apiContact.setId(this.getId());
		apiContact.setCreatedOn(this.getCreatedOn());
		
		return apiContact;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.number = -1;
		this.name = StringUtils.EMPTY;
		this.ownerId = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiContact) {
		super(DatabaseTable.CONTACT);
		
		this.number = apiContact.getNumber();
		this.name = apiContact.getName();
		this.ownerId = apiContact.getOwnerId();
	}
}
