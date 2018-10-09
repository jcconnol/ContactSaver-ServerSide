package edu.uark.commands.contacts;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Contact;
import edu.uark.models.entities.ContactEntity;
import edu.uark.models.repositories.ContactRepository;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactUpdateCommand implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		//Validations
		if (StringUtils.isBlank(this.apiContact.getPhoneNumber())) {
			throw new UnprocessableEntityException("lookupcode");
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
