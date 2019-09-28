package com.cont.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.dataaccess.entities.BaseEntity;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.models.api.Contact;
import com.cont.models.entities.fieldnames.ContactFieldNames;

public class ContactEntity extends BaseEntity<ContactEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.name = rs.getString(ContactFieldNames.NAME);
		this.number = rs.getInt(ContactFieldNames.NUMBER);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.NAME, this.name);
		record.put(ContactFieldNames.NUMBER, this.number);
		
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
		
		apiContact.setName(this.getName());
		apiContact.setCreatedOn(this.getCreatedOn());
		
		return apiContact;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.number = -1;
		this.name = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiProduct) {
		super(DatabaseTable.CONTACT);
		
		this.number = apiProduct.getNumber();
		this.name = apiProduct.getName();
	}
}
