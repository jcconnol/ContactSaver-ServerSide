package com.cont.commands.queries;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.contacts.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Contact;
import com.cont.models.entities.ContactEntity;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactUpdateCommand implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		//Validations
		if (StringUtils.isBlank(this.apiContact.getName())) {
			throw new UnprocessableEntityException("name");
		}
		
		ContactEntity contactEntity = this.contactRepository.get(this.contactName);
		if (contactEntity == null) { //No record with the associated record Name exists in the database.
			throw new NotFoundException("Contact");
		}
		
		this.apiContact = contactEntity.synchronize(this.apiContact); //Synchronize any incoming changes for UPDATE to the database.
		
		contactEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiContact;
	}

	//Properties
	private UUID contactName;
	public UUID getContactName() {
		return this.contactName;
	}
	public ContactUpdateCommand setContactName(UUID contactName) {
		this.contactName = contactName;
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
