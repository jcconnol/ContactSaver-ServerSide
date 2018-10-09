package edu.uark.commands.contacts;

import java.util.UUID;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.models.api.Contact;
import edu.uark.models.entities.ContactEntity;
import edu.uark.models.repositories.ContactRepository;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		ContactEntity contactEntity = this.contactRepository.get(this.contactId);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("Contact");
		}
	}

	//Properties
	private UUID contactId;
	public UUID getContactId() {
		return this.contactId;
	}
	public ContactQuery setContactId(UUID contactId) {
		this.contactId = contactId;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactQuery setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactQuery() {
		this.contactRepository = new ContactRepository();
	}
}
