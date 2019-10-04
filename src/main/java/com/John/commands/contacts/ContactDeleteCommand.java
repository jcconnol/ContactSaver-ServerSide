package com.John.commands.contacts;

import java.util.UUID;

import com.John.commands.VoidCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		ContactEntity contactEntity = this.productRepository.get(this.productId);
		if (contactEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Product");
		}
		
		contactEntity.delete();
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ContactDeleteCommand setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private ContactRepositoryInterface productRepository;
	public ContactRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ContactDeleteCommand setProductRepository(ContactRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ContactDeleteCommand() {
		this.productRepository = new ContactRepository();
	}
}
