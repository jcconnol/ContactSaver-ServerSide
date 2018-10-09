package edu.uark.models.entities;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.models.api.Contact;
import edu.uark.models.entities.fieldnames.ContactFieldNames;

public class ContactEntity extends BaseEntity<ContactEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.phoneNumber = rs.getString(ContactFieldNames.PHONE_NUMBER);
		
		Array contactInfoRS = rs.getArray(ContactFieldNames.CONTACT_INFO);
		this.contactInfo = (String[]) contactInfoRS.getArray();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.PHONE_NUMBER, this.phoneNumber);
		record.put(ContactFieldNames.CONTACT_INFO, this.contactInfo);
		
		return record;
	}

	private String phoneNumber;
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public ContactEntity setPhoneNumber(String phoneNumber) {
		if (!StringUtils.equals(this.phoneNumber, phoneNumber)) {
			this.phoneNumber = phoneNumber;
			this.propertyChanged(ContactFieldNames.PHONE_NUMBER);
		}
		
		return this;
	}

	private String [] contactInfo;
	public String [] getContactInfo() {
		return this.contactInfo;
	}
	//TODO MAKE SET ARRAY DATA
	public ContactEntity setContactInfo(String [] contactInfo) {
		if (this.contactInfo != contactInfo) {
			this.contactInfo = contactInfo;
			this.propertyChanged(ContactFieldNames.CONTACT_INFO);
		}
		
		return this;
	}
	
	public Contact synchronize(Contact apiContact) {
		this.setContactInfo(apiContact.getContactInfo());
		this.setPhoneNumber(apiContact.getPhoneNumber());
		
		return apiContact;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.phoneNumber = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiContact) {
		super(DatabaseTable.CONTACT);
		
		this.contactInfo = apiContact.getContactInfo();
		this.phoneNumber = apiContact.getPhoneNumber();
	}
}
