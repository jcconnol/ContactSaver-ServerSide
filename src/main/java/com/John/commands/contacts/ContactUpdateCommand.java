package com.John.commands.contacts;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.controllers.exceptions.UnprocessableEntityException;
import com.John.models.api.Contact;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactUpdateCommand implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		//Validations
		if (StringUtils.isBlank(this.apiProduct.getLookupCode())) {
			throw new UnprocessableEntityException("lookupcode");
		}

		ContactEntity contactEntity = this.productRepository.get(this.productId);
		if (contactEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Product");
		}
		
		this.apiProduct = contactEntity.synchronize(this.apiProduct); //Synchronize any incoming changes for UPDATE to the database.
		
		contactEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiProduct;
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ContactUpdateCommand setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private Contact apiProduct;
	public Contact getApiProduct() {
		return this.apiProduct;
	}
	public ContactUpdateCommand setApiProduct(Contact apiProduct) {
		this.apiProduct = apiProduct;
		return this;
	}
	
	private ContactRepositoryInterface productRepository;
	public ContactRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ContactUpdateCommand setProductRepository(ContactRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ContactUpdateCommand() {
		this.productRepository = new ContactRepository();
	}
}
