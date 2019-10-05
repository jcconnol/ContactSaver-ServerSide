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
		this.id = rs.getString(ContactFieldNames.ID);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.NAME, this.name);
		record.put(ContactFieldNames.NUMBER, this.number);
		record.put(ContactFieldNames.ID, this.id);
		
		return record;
	}
	
	private String id;
	public String getContactId() {
		return this.id;
	}
	public ContactEntity setContactId(String id) {
		this.id = id;
		this.propertyChanged(ContactFieldNames.ID);
		
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
		this.setContactId(apiContact.getId());
		
		apiContact.setCreatedOn(this.getCreatedOn());
		
		return apiContact;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.number = -1;
		this.name = StringUtils.EMPTY;
		this.id = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiContact) {
		super(DatabaseTable.CONTACT);
		
		this.number = apiContact.getNumber();
		this.name = apiContact.getName();
		this.id = apiContact.getId();
	}
}
