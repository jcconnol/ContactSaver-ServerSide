package com.John.commands.contacts;

import org.apache.commons.lang3.StringUtils;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.controllers.exceptions.UnprocessableEntityException;
import com.John.models.api.Contact;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactByLookupCodeQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		if (StringUtils.isBlank(this.lookupCode)) {
			throw new UnprocessableEntityException("lookupcode");
		}
		
		ContactEntity contactEntity = this.productRepository.byLookupCode(this.lookupCode);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Product");
		}
	}

	//Properties
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ContactByLookupCodeQuery setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private ContactRepositoryInterface productRepository;
	public ContactRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ContactByLookupCodeQuery setProductRepository(ContactRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ContactByLookupCodeQuery() {
		this.productRepository = new ContactRepository();
	}
}
