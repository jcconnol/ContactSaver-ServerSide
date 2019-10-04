package com.John.commands.contacts;

import java.util.UUID;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.models.api.Contact;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		ContactEntity contactEntity = this.productRepository.get(this.productId);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Product");
		}
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ContactQuery setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private ContactRepositoryInterface productRepository;
	public ContactRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ContactQuery setProductRepository(ContactRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ContactQuery() {
		this.productRepository = new ContactRepository();
	}
}
