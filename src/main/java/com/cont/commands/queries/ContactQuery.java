package com.cont.commands.queries;

import java.util.UUID;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.api.Contact;
import com.cont.models.entities.ContactEntity;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		ContactEntity contactEntity = this.contactRepository.get(this.contactName);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Contact");
		}
	}

	//Properties
	private UUID contactName;
	public UUID getContactName() {
		return this.contactName;
	}
	public ContactQuery setContactName(UUID contactName) {
		this.contactName = contactName;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactQuery setProductRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactQuery() {
		this.contactRepository = new ContactRepository();
	}
}
