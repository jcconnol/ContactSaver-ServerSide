package com.John.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.John.dataaccess.entities.BaseEntity;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.models.api.Contact;
import com.John.models.entities.fieldnames.ContactFieldNames;

public class ContactEntity extends BaseEntity<ContactEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.name = rs.getString(ContactFieldNames.NAME);
		this.count = rs.getInt(ContactFieldNames.NUMBER);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.NAME, this.name);
		record.put(ContactFieldNames.NUMBER, this.count);
		
		return record;
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

	private int count;
	public int getCount() {
		return this.count;
	}
	public ContactEntity setCount(int count) {
		if (this.count != count) {
			this.count = count;
			this.propertyChanged(ContactFieldNames.NUMBER);
		}
		
		return this;
	}
	
	public Contact synchronize(Contact apiContact) {
		this.setCount(apiContact.getNumber());
		this.setName(apiContact.getName());
		
		apiContact.setId(this.getId());
		apiContact.setCreatedOn(this.getCreatedOn());
		
		return apiContact;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.count = -1;
		this.name = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiContact) {
		super(DatabaseTable.CONTACT);
		
		this.count = apiContact.getNumber();
		this.name = apiContact.getName();
	}
}
