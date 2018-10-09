package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import edu.uark.models.entities.ContactEntity;

public class Contact {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Contact setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private String phoneNumber;
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public Contact setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	//TODO make set array data
	private String [] contactInfo;
	public String [] getContactInfo() {
		return this.contactInfo;
	}
	public Contact setContactInfo(String [] contactInfo) {
		this.contactInfo = contactInfo;
		return this;
	}
	
	public Contact() {
		this.phoneNumber = "8675309";
		this.id = new UUID(0, 0);
	}
	
	public Contact(ContactEntity contactEntity) {
		this.id = contactEntity.getId();
		this.phoneNumber = contactEntity.getPhoneNumber();
	}
}
