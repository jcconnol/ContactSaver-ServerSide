package com.cont.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cont.models.entities.ContactEntity;

public class Contact {
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
		this.name = "";
		this.number = 0;
		this.createdOn = LocalDateTime.now();
	}
	
	public Contact(ContactEntity contactEntity) {
		this.number = contactEntity.getNumber();
		this.createdOn = contactEntity.getCreatedOn();
		this.name = contactEntity.getName();
	}
}
