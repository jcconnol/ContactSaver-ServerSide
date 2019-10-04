package com.John.commands.contacts;

import org.apache.commons.lang3.StringUtils;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.controllers.exceptions.UnprocessableEntityException;
import com.John.models.api.Contact;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactByNameQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		if (StringUtils.isBlank(this.Name)) {
			throw new UnprocessableEntityException("name");
		}
		
		ContactEntity contactEntity = this.contactRepository.byName(this.Name);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Contact");
		}
	}

	//Properties
	private String Name;
	public String getName() {
		return this.Name;
	}
	public ContactByNameQuery setName(String Name) {
		this.Name = Name;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactByNameQuery setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactByNameQuery() {
		this.contactRepository = new ContactRepository();
	}
}
