package com.John.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import com.John.models.entities.ContactEntity;

public class Contact {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Contact setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public Contact setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private int count;
	public int getCount() {
		return this.count;
	}
	public Contact setCount(int count) {
		this.count = count;
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Contact setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	public Contact() {
		this.count = -1;
		this.lookupCode = "";
		this.id = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
	}
	
	public Contact(ContactEntity contactEntity) {
		this.id = contactEntity.getId();
		this.count = contactEntity.getCount();
		this.createdOn = contactEntity.getCreatedOn();
		this.lookupCode = contactEntity.getLookupCode();
	}
}
