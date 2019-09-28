package com.cont.commands.queries;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Contact;
import com.cont.models.entities.ContactEntity;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactByNameQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		if (StringUtils.isBlank(this.name)) {
			throw new UnprocessableEntityException("name");
		}
		
		ContactEntity contactEntity = this.contactRepository.byName(this.name);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Contact");
		}
	}

	//Properties
	private String name;
	public String getName() {
		return this.name;
	}
	public ContactByNameQuery setName(String name) {
		this.name = name;
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
