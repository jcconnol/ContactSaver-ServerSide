package com.cont.commands.queries;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.ConflictException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Contact;
import com.cont.models.entities.ContactEntity;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactCreateCommand implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		//Validations
		if (StringUtils.isBlank(this.apiContact.getName())) {
			throw new UnprocessableEntityException("name");
		}

		ContactEntity contactEntity = this.contactRepository.byName(this.apiContact.getName());
		if (contactEntity != null) {
			throw new ConflictException("name"); //Lookupcode already defined for another product.
		}
		
		//No ENTITY object was returned from the database, thus the API object's lookupcode must be unique.
		contactEntity = new ContactEntity(apiContact); //Create a new ENTITY object from the API object details.
		contactEntity.save(); //Write, via an INSERT, the new record to the database.
		
		this.apiContact.setName(contactEntity.getName()); //Synchronize information generated by the database upon INSERT.
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
