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
		if (StringUtils.isBlank(this.apiContact.getName())) {
			throw new UnprocessableEntityException("name");
		}

		ContactEntity contactEntity = this.contactRepository.get(this.contactId);
		if (contactEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Contact");
		}
		
		this.apiContact = contactEntity.synchronize(this.apiContact); //Synchronize any incoming changes for UPDATE to the database.
		
		contactEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiContact;
	}

	//Properties
	private UUID contactId;
	public UUID getContactId() {
		return this.contactId;
	}
	public ContactUpdateCommand setContactId(UUID contactId) {
		this.contactId = contactId;
		return this;
	}
	
	private Contact apiContact;
	public Contact getApiContact() {
		return this.apiContact;
	}
	public ContactUpdateCommand setApiContact(Contact apiContact) {
		this.apiContact = apiContact;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactUpdateCommand setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactUpdateCommand() {
		this.contactRepository = new ContactRepository();
	}
}
