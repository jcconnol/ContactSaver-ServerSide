package com.John.commands.contacts;

import org.apache.commons.lang3.StringUtils;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.ConflictException;
import com.John.controllers.exceptions.UnprocessableEntityException;
import com.John.models.api.Contact;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactCreateCommand implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		//Validations
		if (StringUtils.isBlank(this.apiContact.getName())) {
			throw new UnprocessableEntityException("name");
		}

		ContactEntity contactEntity = this.contactRepository.byName(this.apiContact.getName());
		if (contactEntity != null) {
			throw new ConflictException("name"); //Lookupcode already defined for another contact.

		}
		
		//No ENTITY object was returned from the database, thus the API object's lookupcode must be unique.
		contactEntity = new ContactEntity(apiContact); //Create a new ENTITY object from the API object details.
		contactEntity.save(); //Write, via an INSERT, the new record to the database.
		
		this.apiContact.setId(contactEntity.getId()); //Synchronize information generated by the database upon INSERT.
		this.apiContact.setCreatedOn(contactEntity.getCreatedOn());

		return this.apiContact;
	}

	//Properties
	private Contact apiContact;
	public Contact getApiContact() {
		return this.apiContact;
	}
	public ContactCreateCommand setApiContact(Contact apiContact) {
		this.apiContact = apiContact;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactCreateCommand setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactCreateCommand() {
		this.contactRepository = new ContactRepository();
	}
}
