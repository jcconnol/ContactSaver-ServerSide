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
	
	private String name;
	public String getName() {
		return this.name;
	}
	public Contact setName(String name) {
		this.name = name;
		return this;
	}
	
	private int number;
	public int getNumber() {
		return this.number;
	}
	public Contact setNumber(int number) {
		this.number = number;
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
		this.number = -1;
		this.name = "";
		this.id = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
	}
	
	public Contact(ContactEntity contactEntity) {
		this.id = contactEntity.getId();
		this.number = contactEntity.getCount();
		this.createdOn = contactEntity.getCreatedOn();
		this.name = contactEntity.getName();
	}
}
